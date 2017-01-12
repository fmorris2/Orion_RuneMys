package org.missions.data.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public enum RM_QuestNPC {

    DUKE_HORACIO("Duke Horacio", new Area(new Position[]{
            new Position(3204, 3226, 1),
            new Position(3213, 3226, 1),
            new Position(3213, 3216, 1),
            new Position(3204, 3216, 1)
    })),
    SEDRIDOR("Sedridor", new Area(new Position[]{
            new Position(3108, 9565, 0),
            new Position(3095, 9565, 0),
            new Position(3095, 9575, 0),
            new Position(3108, 9575, 0)
    })),
    AUBURY("Aubury", new Area(new Position[]{
            new Position(3257, 3405, 0),
            new Position(3257, 3398, 0),
            new Position(3249, 3398, 0),
            new Position(3249, 3405, 0)
    }));

    private final String NPC_NAME;
    private final Area NPC_AREA;

    RM_QuestNPC(String npc_name, Area npc_area) {
        this.NPC_NAME = npc_name;
        this.NPC_AREA = npc_area;
    }

    public String getNPCName() {
        return NPC_NAME;
    }

    public Area getNPCArea() {
        return NPC_AREA;
    }

}

