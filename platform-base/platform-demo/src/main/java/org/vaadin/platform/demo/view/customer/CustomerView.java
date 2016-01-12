package org.vaadin.platform.demo.view.customer;

import org.vaadin.platform.demo.Views;
import org.vaadin.platform.ui.navigation.IsNavigable;
import org.vaadin.platform.ui.view.HasPresenter;
import org.vaadin.platform.ui.view.ViewComposition;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@ViewComposition(name = Views.CUSTOMER_VIEW)
public class CustomerView extends CustomComponent implements IsNavigable, HasPresenter<CustomerViewPresenter> {
    private static final long serialVersionUID = -8457497740824005459L;

    private VerticalLayout layout;

    public CustomerView() {
        layout = new VerticalLayout();
        layout.setMargin(true);
        setCompositionRoot(layout);

        Button button = new Button("Click", e -> getPresenter().onButtonClicked());
        layout.addComponent(button);
    }

    @Override
    public Class<CustomerViewPresenter> getPresenterType() {
        return CustomerViewPresenter.class;
    }

    public void showNotification() {
        Notification.show("Button clicked!");
    }
}
