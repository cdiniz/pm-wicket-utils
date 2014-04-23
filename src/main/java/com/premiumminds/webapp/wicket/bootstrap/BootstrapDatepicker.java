package com.premiumminds.webapp.wicket.bootstrap;

import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.IGenericComponent;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

import com.premiumminds.webapp.wicket.bootstrap.datepicker.BootstrapDatePickerBehaviour;

/**
 * Bootstrap Datepicker for Wicket.
 *
 * @author acamilo
 * @see <a href="https://github.com/eternicode/bootstrap-datepicker">https://github.com/eternicode/bootstrap-datepicker</a>
 */
public class BootstrapDatepicker extends WebMarkupContainer implements IGenericComponent<Date> {
	private static final long serialVersionUID = -117683073963817461L;

	private DateTextField dateField;
	
	/**
	 * Instantiates a new bootstrap datepicker.
	 *
	 * @param id the wicket:id
	 */
	public BootstrapDatepicker(String id) {
		super(id);
		
		add(new BootstrapDatePickerBehaviour());
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.Component#onConfigure()
	 */
	@Override
	protected void onConfigure() {
		super.onConfigure();
		
		dateField = getDateTextField();
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.Component#onComponentTag(org.apache.wicket.markup.ComponentTag)
	 */
	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		
		if(dateField!=null){
			tag.put("data-date-format", dateField.getTextFormat().toLowerCase());
		}
	}

	/**
	 * Gets the DateTextField inside this datepicker wrapper.
	 *
	 * @return the date field
	 */
	public DateTextField getDateTextField(){
		for(Component component : visitChildren(DateTextField.class)){
			return (DateTextField) component;
		}
		throw new WicketRuntimeException("BootstrapDatepicker didn't have any DateTextField child!");
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.IGenericComponent#getModel()
	 */
	@Override
	public IModel<Date> getModel() {
		return getDateTextField().getModel();
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.IGenericComponent#setModel(org.apache.wicket.model.IModel)
	 */
	@Override
	public void setModel(IModel<Date> model) {
		getDateTextField().setModel(model);
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.IGenericComponent#setModelObject(java.lang.Object)
	 */
	@Override
	public void setModelObject(Date object) {
		getDateTextField().setModelObject(object);
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.IGenericComponent#getModelObject()
	 */
	@Override
	public Date getModelObject() {
		return getDateTextField().getModelObject();
	}
	
}
