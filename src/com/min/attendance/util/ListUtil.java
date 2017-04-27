package com.min.attendance.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public class ListUtil {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List RsToList(ResultSet rs) {
		List list = new ArrayList();
		try {
			ResultSetMetaData md = rs.getMetaData();
			int num = md.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>(num);
				for (int i = 1; i <= num; i++) {
					map.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	public static String listToJson(List list) {
		JSONArray json = new JSONArray();
		if (list.size() > 0) {
			json = JSONArray.fromObject(list);
		}
		return json.toString();
	}
}
