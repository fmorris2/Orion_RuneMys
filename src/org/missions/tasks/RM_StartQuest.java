package org.missions.tasks;

import org.missions.OrionRuneMys;
import org.missions.data.enums.RM_QuestNPC;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class RM_StartQuest extends Task<OrionRuneMys> {

    private NPC duke_horacio;

    public RM_StartQuest(OrionRuneMys mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(63) == 0;
    }

    @Override
    public void execute() {
        duke_horacio = npcs.closest(RM_QuestNPC.DUKE_HORACIO.getNPCArea().setPlane(1), RM_QuestNPC.DUKE_HORACIO.getNPCName());
        if (duke_horacio != null && map.canReach(duke_horacio)) {
            iFact.dialogue("Talk-to", RM_QuestNPC.DUKE_HORACIO.getNPCName(), 20, 1).execute();
        } else {
            if (walkUtils.walkToArea(RM_QuestNPC.DUKE_HORACIO.getNPCArea().setPlane(1), () -> {
                duke_horacio = npcs.closest(RM_QuestNPC.DUKE_HORACIO.getNPCArea().setPlane(1), RM_QuestNPC.DUKE_HORACIO.getNPCName());
                return duke_horacio != null && duke_horacio.isVisible() && map.canReach(duke_horacio);
            })) {
                Timing.waitCondition(() -> npcs.closest(RM_QuestNPC.DUKE_HORACIO.getNPCArea().setPlane(1), RM_QuestNPC.DUKE_HORACIO.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Starting Rune Mysteries";
    }

}

