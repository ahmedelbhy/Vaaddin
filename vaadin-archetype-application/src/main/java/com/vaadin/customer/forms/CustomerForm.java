package com.vaadin.customer.forms;

import com.vaadin.customer.models.Customer;
import com.vaadin.customer.models.CustomerStatus;
import com.vaadin.customer.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.viewform.MainView;

public class CustomerForm extends FormLayout {

	private TextField id = new TextField("ID");
	private TextField firstName = new TextField("First name");
	private TextField lastName = new TextField("Last name");
	private TextField age = new TextField("Age");
	private TextField address = new TextField("Address");

	private ComboBox<CustomerStatus> status = new ComboBox<>("Status");
	private DatePicker birthDate = new DatePicker("Birthdate");

	private Button save = new Button("Save");
	private Button delete = new Button("Delete");

	private Binder<Customer> binder = new Binder<>(Customer.class);
	private CustomerService customerService = CustomerService.INSTANCE;
	private MainView mainView;

	public CustomerForm() {
		status.setItems(CustomerStatus.values());

		HorizontalLayout buttons = new HorizontalLayout(save, delete);
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		add(id, firstName, lastName, age, address, buttons);
		
		binder.bindInstanceFields(this);
	}

	public CustomerForm(MainView mainView) {
		this.mainView = mainView;

		status.setItems(CustomerStatus.values());

		HorizontalLayout buttons = new HorizontalLayout(save, delete);
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		add(id, firstName, lastName, age, address, status, birthDate, buttons);
	
		binder.bindInstanceFields(this);

		save.addClickListener(event -> save());
		delete.addClickListener(event -> delete());
	}

	private void delete() {
		Customer customer = binder.getBean();
		customerService.delete(customer);
		mainView.updateList();
		setCustomer(null);
	}

	private void save() {
		Customer customer = binder.getBean();
		customerService.add(customer);
		mainView.updateList();
		setCustomer(null);
	}

	public void setCustomer(Customer customer) {
		binder.setBean(customer);

		if (customer == null) {
			setVisible(false);
		} else {
			setVisible(true);
			firstName.focus();
		}
	}
}
