/**
 * Copyright (C) 2014 Premium Minds.
 *
 * This file is part of pm-wicket-utils.
 *
 * pm-wicket-utils is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * pm-wicket-utils is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with pm-wicket-utils. If not, see <http://www.gnu.org/licenses/>.
 */
package com.premiumminds.webapp.wicket.bootstrap;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackCollector;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;

public class BootstrapControlGroupFeedback extends WebMarkupContainer implements IFeedback {
	private static final long serialVersionUID = -4902896529648117240L;
	
	private IFeedbackMessageFilter filter;
	
	public BootstrapControlGroupFeedback(String id) {
		super(id);
	}
	
	@Override
	protected void onConfigure() {
		super.onConfigure();
		
		filter = new IFeedbackMessageFilter() {
			private static final long serialVersionUID = -7726392072697648969L;

			public boolean accept(FeedbackMessage msg) {
				for(Component component : visitChildren(FormComponent.class)){
					if(component.equals(msg.getReporter())) return true;
				}
				return false;
			}
		};
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		
		response.render(OnDomReadyHeaderItem.forScript("$(\"#"+this.getMarkupId(true)+"\").focusin(function(){ $(this).removeClass(\"error\"); })"));
	}
	
	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		
		if(new FeedbackCollector(getPage()).collect(filter).size() > 0) tag.append("class", "has-error", " ");
	}
	
}
