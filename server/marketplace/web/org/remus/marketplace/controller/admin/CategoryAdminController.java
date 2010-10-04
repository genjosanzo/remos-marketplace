/*******************************************************************************
 * Copyright (c) 2010 Tom Seidel, Remus Software
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *
 * Contributors:
 *     Tom Seidel - initial API and implementation
 *******************************************************************************/
package org.remus.marketplace.controller.admin;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.remus.marketplace.dao.AdvancedCriteria;
import org.remus.marketplace.dao.gen.CategoryDao;
import org.remus.marketplace.dao.gen.NodeDao;
import org.remus.marketplace.entities.Category;
import org.remus.marketplace.entities.Node;
import org.remus.marketplace.form.AddSolutionForm;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
public class CategoryAdminController extends SimpleFormController {

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private NodeDao nodeDao;

	public void setNodeDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
	}

	private String serverPrefix;

	public void setServerPrefix(String serverPrefix) {
		this.serverPrefix = serverPrefix;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Object formBackingObject = super.formBackingObject(request);
		((AddSolutionForm) formBackingObject).setCategory(Integer
				.parseInt(request.getParameter("categoryId")));
		return formBackingObject;
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		String parameter = request.getParameter("categoryId");
		Category findById = categoryDao.findById(Integer.parseInt(parameter));

		List<Integer> ids = new ArrayList<Integer>();
		Set<Node> nodes = findById.getNodes();
		for (Node node : nodes) {
			ids.add(node.getId());
		}
		AdvancedCriteria adv = AdvancedCriteria.EMPTY_READONLY_INSTANCE;
		if (ids.size() > 0) {
			adv = new AdvancedCriteria().addRestriction(Restrictions
					.not(Restrictions.in(Node.ID, ids)));
		}
		List<Node> find = nodeDao.find(adv);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("category", findById);
		response.put("availableSolutions", find);
		return response;

	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Node.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				Node findById = nodeDao.findById(Integer.parseInt(text));
				setValue(findById);
			}
		});

	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		AddSolutionForm form = (AddSolutionForm) command;
		Integer categoryId = form.getCategory();
		Node findById = nodeDao.findById(form.getNode().getId());
		Category findById2 = categoryDao.findById(categoryId);
		findById2.getNodes().add(findById);
		categoryDao.update(findById2);
		return new ModelAndView(new RedirectView(categoryId + ".htm", true));
	}

}
