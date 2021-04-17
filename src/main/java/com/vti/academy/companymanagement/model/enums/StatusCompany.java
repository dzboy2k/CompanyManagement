package com.vti.academy.companymanagement.model.enums;

public enum StatusCompany {
	STOPPED((byte) 0), WORKING((byte) 1), PENDING((byte) 2);
	
	private byte value;

    StatusCompany(byte i) { this.value = i; }    

    public byte getValue() { return value; }

    public static StatusCompany parse(Byte id) {
        StatusCompany status = null; // Default
        if(id == null) return status;
        
        for (StatusCompany item : StatusCompany.values()) {
            if (item.getValue()==id) {
                status = item;
                break;
            }
        }
        return status;
    }
}
