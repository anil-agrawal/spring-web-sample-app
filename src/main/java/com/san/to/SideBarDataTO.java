package com.san.to;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SideBarDataTO {

	public SideBarDataTO() {
		Map<String, Object> itemData = null;
		Map<String, Object> childItemData = null;
		Map<String, Object> subChildItemData = null;
		List<Map<String, Object>> items = null;
		List<Map<String, Object>> subItems = null;

		// DashBoard Item Details
		itemData = new HashMap<String, Object>();
		itemData.put("label", "Dashboard");
		itemData.put("iconClass", "fa-dashboard");
		itemData.put("link", "/");
		itemData.put("hasChildItems", false);
		itemList.add(itemData);

		// Charts Item Details
		itemData = new HashMap<String, Object>();
		itemData.put("label", "Charts");
		itemData.put("iconClass", "fa-bar-chart-o");
		itemData.put("link", "N/A");
		itemData.put("hasChildItems", true);
		items = new ArrayList<Map<String, Object>>();
		itemData.put("items", items);
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Flot Charts");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/views/flot.jsp");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Morris.js Charts");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/views/morris.jsp");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		itemList.add(itemData);

		// Tables Item Details
		itemData = new HashMap<String, Object>();
		itemData.put("label", "Tables");
		itemData.put("iconClass", "fa-table");
		itemData.put("link", "/views/tables.jsp");
		itemData.put("hasChildItems", false);
		itemList.add(itemData);

		// Forms Item Details
		itemData = new HashMap<String, Object>();
		itemData.put("label", "Forms");
		itemData.put("iconClass", "fa-edit");
		itemData.put("link", "/views/forms.jsp");
		itemData.put("hasChildItems", false);
		itemList.add(itemData);

		// UI Elements Item Details
		itemData = new HashMap<String, Object>();
		itemData.put("label", "UI Elements");
		itemData.put("iconClass", "fa-wrench");
		itemData.put("link", "N/A");
		itemData.put("hasChildItems", true);
		items = new ArrayList<Map<String, Object>>();
		itemData.put("items", items);
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Panels and Wells");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/views/panels-wells.jsp");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Buttons");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/views/buttons.jsp");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Notifications");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/views/notifications.jsp");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Typography");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/views/typography.jsp");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Icons");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/views/icons.jsp");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Grid");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/views/grid.jsp");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		itemList.add(itemData);

		// Multi-Level Dropdown Item Details
		itemData = new HashMap<String, Object>();
		itemData.put("label", "Multi-Level Dropdown");
		itemData.put("iconClass", "fa-sitemap");
		itemData.put("link", "N/A");
		itemData.put("hasChildItems", true);
		items = new ArrayList<Map<String, Object>>();
		itemData.put("items", items);
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Second Level Item 1");
			childItemData.put("iconClass", "");
			childItemData.put("link", "#");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Second Level Item 2");
			childItemData.put("iconClass", "");
			childItemData.put("link", "#");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Third Level");
			childItemData.put("iconClass", "");
			childItemData.put("link", "#");
			childItemData.put("hasChildItems", true);
			items.add(childItemData);
			subItems = new ArrayList<Map<String, Object>>();
			childItemData.put("items", subItems);
			{
				subChildItemData = new HashMap<String, Object>();
				subChildItemData.put("label", "Third Level Item 1");
				subChildItemData.put("iconClass", "");
				subChildItemData.put("link", "#");
				subChildItemData.put("hasChildItems", false);
				subItems.add(subChildItemData);
			}
			{
				subChildItemData = new HashMap<String, Object>();
				subChildItemData.put("label", "Third Level Item 2");
				subChildItemData.put("iconClass", "");
				subChildItemData.put("link", "#");
				subChildItemData.put("hasChildItems", false);
				subItems.add(subChildItemData);
			}
			{
				subChildItemData = new HashMap<String, Object>();
				subChildItemData.put("label", "Third Level Item 3");
				subChildItemData.put("iconClass", "");
				subChildItemData.put("link", "#");
				subChildItemData.put("hasChildItems", false);
				subItems.add(subChildItemData);
			}
		}
		itemList.add(itemData);

		// Sample Pages Item Details
		itemData = new HashMap<String, Object>();
		itemData.put("label", "Sample Pages");
		itemData.put("iconClass", "fa-files-o");
		itemData.put("link", "N/A");
		itemData.put("hasChildItems", true);
		items = new ArrayList<Map<String, Object>>();
		itemData.put("items", items);
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Blank Page");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/views/blank.jsp");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Login Page");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/sec/login");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		{
			childItemData = new HashMap<String, Object>();
			childItemData.put("label", "Grid Stack");
			childItemData.put("iconClass", "");
			childItemData.put("link", "/views/testGridStack.jsp");
			childItemData.put("hasChildItems", false);
			items.add(childItemData);
		}
		itemList.add(itemData);

	}

	private List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();

	public List<Map<String, Object>> getItemList() {
		return itemList;
	}

	public void setItemList(List<Map<String, Object>> itemList) {
		this.itemList = itemList;
	}
}
