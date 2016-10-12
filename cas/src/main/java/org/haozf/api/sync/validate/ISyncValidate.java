package org.haozf.api.sync.validate;

public interface ISyncValidate {
	public int checkSourceidExist(String sourceid);

	public int checkSourceidExist(String[] sourceids);
}
