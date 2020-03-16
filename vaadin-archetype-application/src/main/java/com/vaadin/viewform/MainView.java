package com.vaadin.viewform;

import com.vaadin.customer.forms.CustomerForm;
import com.vaadin.customer.models.Customer;
import com.vaadin.customer.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7443039713060012161L;
	private CustomerService customerService = CustomerService.INSTANCE;

	private CustomerForm form = new CustomerForm(this);

	private TextField filterText = new TextField();

	private Grid<Customer> grid = new Grid<>(Customer.class);

	public MainView() {

		filterText.setPlaceholder("Filter by name...");
		filterText.setClearButtonVisible(true);
		filterText.setValueChangeMode(ValueChangeMode.EAGER);
		filterText.addValueChangeListener(e -> updateListbyName());

		Button addCustomerBtn = new Button("Add new customer");
		addCustomerBtn.addClickListener(e -> {
			grid.asSingleSelect().clear();
			form.setCustomer(new Customer());
		});

		HorizontalLayout toolbar = new HorizontalLayout(filterText, addCustomerBtn);

		grid.setColumns("id", "firstName", "lastName", "age", "address", "status", "birthDate");

		HorizontalLayout mainContent = new HorizontalLayout(grid, form);
		mainContent.setSizeFull();
		grid.setSizeFull();

		form.setCustomer(null);

		grid.asSingleSelect().addValueChangeListener(event -> form.setCustomer(grid.asSingleSelect().getValue()));

		add(toolbar, mainContent);

		setSizeFull();

		updateList();
	}

	private void updateListbyName() {
		if (filterText.getValue().isEmpty())
			grid.setItems(customerService.findAll());

		Customer custData = customerService.findByName(filterText.getValue());
		if (custData != null) {
			grid.setItems(custData);
		}
	}

	public void updateList() {
		grid.setItems(customerService.findAll());
	}
}
