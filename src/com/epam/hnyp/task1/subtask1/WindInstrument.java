package com.epam.hnyp.task1.subtask1;

public abstract class WindInstrument extends MusicInstrument {
	private static final String DEFAULT_MATERIAL = "bronze";
	
	private String material;
	
	public WindInstrument() {
		this.material = DEFAULT_MATERIAL;
	}
	
	public WindInstrument(String vendor, int year, String material) {
		super(vendor, year);
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			if (obj instanceof WindInstrument) {
//				if (obj.hashCode() != this.hashCode()) {
//					return false;
//				}
				WindInstrument wi = (WindInstrument)obj;
				if (wi.material.equals(this.material)) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + material.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(super.toString()).append(", wind instrument, ").append("material ").append(material);
		return str.toString(); 
	}
}
