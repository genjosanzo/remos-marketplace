/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved.
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/

package org.remus.marketplace.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.dao.gen.InstallableUnitDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.dao.gen.PlatformDao;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.entities.Platform;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class NodeAdminController extends SimpleFormController {

	private NodeDao nodeDao;

	private InstallableUnitDao installableUnitDao;

	private PlatformDao platformDao;

	public void setPlatformDao(PlatformDao platformDao) {
		this.platformDao = platformDao;
	}

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	public void setInstallableUnitDao(InstallableUnitDao installableUnitDao) {
		this.installableUnitDao = installableUnitDao;
	}

	private final ThreadLocal<Node> cachedNode = new ThreadLocal<Node>();

	private final ThreadLocal<Map<Integer, Platform>> platformCache = new ThreadLocal<Map<Integer, Platform>>() {
		@Override
		protected java.util.Map<Integer, Platform> initialValue() {
			return new HashMap<Integer, Platform>();
		};
	};

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

		binder.registerCustomEditor(Set.class, Node.PLATFORMS,
				new CustomCollectionEditor(Set.class) {

					@Override
					protected Object convertElement(Object element) {
						if (element instanceof String) {
							try {
								return platformCache.get().get(
										Integer.parseInt((String) element));
							} catch (NumberFormatException e) {
								return null;
							}
						}
						return null;
					}
					// @Override
					// public void setAsText(String text)
					// throws IllegalArgumentException {
					// String[] split = text.split(",");
					// Set<Platform> platforms = new HashSet<Platform>();
					// for (String string : split) {
					// Platform findById = platformDao.findById(Integer
					// .valueOf(string));
					// platforms.add(findById);
					// }
					// setValue(platforms);
					// }
				});

	}

	@Override
	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		Node node = (Node) command;
		if (StringUtils.isEmpty(node.getName())) {
			errors.rejectValue(Node.NAME, "empty.value",
					"Name must not be empty");
		}
		if (StringUtils.isEmpty(node.getLicense())) {
			errors.rejectValue(Node.LICENSE, "empty.value",
					"License not be empty");
		}
		if (node.getFoundationmember() == null
				|| node.getFoundationmember() != 1
				&& node.getFoundationmember() != 0) {
			errors.rejectValue(Node.FOUNDATIONMEMBER, "wrong.value",
					"Please enter 0 or 1 as value");
		}
		if (StringUtils.isEmpty(node.getShortdescription())) {
			errors.rejectValue(Node.SHORTDESCRIPTION, "empty.value",
					"Value must not be empty");
		}

		super.onBindAndValidate(request, command, errors);
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		int parseInt = Integer.parseInt(request.getParameter("nodeId"));
		Node findById = nodeDao.findById(parseInt);
		cachedNode.set(findById);
		return findById;
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {

		Map<String, Object> response = new HashMap<String, Object>();

		if (platformCache.get().size() == 0) {
			List<Platform> find2 = platformDao
					.find(AdvancedCriteria.EMPTY_READONLY_INSTANCE);
			for (Platform platform : find2) {
				platformCache.get().put(platform.getId(), platform);
			}

		}
		int nodeId = Integer.parseInt(request.getParameter("nodeId"));
		// List<InstallableUnit> findByNodeId = installableUnitDao.findByNodeId(
		// nodeId, null);
		response.put("platforms", platformCache.get().values());
		response.put("ius", cachedNode.get().getInstallableUnits());
		response.put("nodeId", nodeId);
		return response;

	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Node node = (Node) command;
		nodeDao.update(node);

		return new ModelAndView(new RedirectView(node.getId() + ".htm", true));
	}

}
