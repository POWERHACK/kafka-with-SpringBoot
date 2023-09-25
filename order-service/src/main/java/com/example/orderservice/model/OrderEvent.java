package com.example.orderservice.model;

public class OrderEvent{

    private String message;
    private String status;
    private Orders orders;

    public OrderEvent() {
    	
    }
    

    public OrderEvent(String message, String status, Orders orders) {
		super();
		this.message = message;
		this.status = status;
		this.orders = orders;
	}


	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderEvents{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", orders=" + orders +
                '}';
    }
}
