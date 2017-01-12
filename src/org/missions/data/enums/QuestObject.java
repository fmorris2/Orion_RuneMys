package org.missions.data.enums;

import org.osbot.rs07.api.map.Area;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public enum QuestObject {

    CADAVA_BERRY("Pick-from", 753, new int[]{23625, 23626}, new Area(new int[][]{
            {3264, 3374},
            {3274, 3374},
            {3273, 3364},
            {3264, 3365}
    })),
    MESSAGE(null, 755, null, null),
    CADAVA_POTION(null, 756, null, null);

    private final String ACTION;
    private final int ITEM_ID;
    private final int[] OBJECT_IDS;
    private final Area OBJECT_AREA;

    QuestObject(String action, int item_id, int[] object_ids, Area object_area) {
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

