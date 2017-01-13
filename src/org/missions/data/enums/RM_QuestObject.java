package org.missions.data.enums;

import org.osbot.rs07.api.map.Area;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public enum RM_QuestObject {

    AIR_TALISMAN(null, 1438, null, null),
    BOX(null, 290, null, null),
    NOTE(null, 291, null, null);

    private final String ACTION;
    private final int ITEM_ID;
    private final int[] OBJECT_IDS;
    private final Area OBJECT_AREA;

    RM_QuestObject(String action, int item_id, int[] object_ids, Area object_area) {
        this.ACTION = action;
        this.ITEM_ID = item_id;
        this.OBJECT_IDS = object_ids;
        this.OBJECT_AREA = object_area;
    }

    public String getAction() {
        return ACTION;
    }

    public int getItemID() {
        return ITEM_ID;
    }

    public int[] getObjectIDs() {
        return OBJECT_IDS;
    }

    public Area getObjectArea() {
        return OBJECT_AREA;
    }

}

