package com.vti.academy.companymanagement.model.enums;

public enum StatusUser {
	LEAVES((byte) 0), WORKING((byte) 1), PENDING((byte) 2);
	
	private byte value;

	StatusUser(byte i) { this.value = i; }    

    public byte getValue() { return value; }

    public static StatusUser parse(Byte id) {
    	StatusUser status = null; // Default
        if(id == null) return status;
        
        for (StatusUser item : StatusUser.values()) {
            if (item.getValue()==id) {
                status = item;
                break;
            }
        }
        return status;
    }
}
