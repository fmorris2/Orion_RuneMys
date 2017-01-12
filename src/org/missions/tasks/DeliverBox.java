package org.missions.tasks;

import org.missions.OrionRuneMys;
import org.missions.data.enums.QuestNPC;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class DeliverBox extends Task<OrionRuneMys> {

    private NPC aubury;

    public DeliverBox(OrionRuneMys mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(63) == 3 || configs.get(63) == 4;
    }

    @Override
    public void execute() {
        aubury = npcs.closest(QuestNPC.AUBURY.getNPCArea(), QuestNPC.AUBURY.getNPCName());
        if (aubury != null && map.canReach(aubury)) {
            iFact.dialogue("Talk-to", QuestNPC.AUBURY.getNPCName(), 20, 3).execute();
        } else {
            if (walkUtils.walkToArea(QuestNPC.AUBURY.getNPCArea(), () -> {
                aubury = npcs.closest(QuestNPC.AUBURY.getNPCArea(), QuestNPC.AUBURY.getNPCName());
                return aubury != null && aubury.isVisible() && map.canReach(aubury);
            })) {
                Timing.waitCondition(() -> npcs.closest(QuestNPC.AUBURY.getNPCArea(), QuestNPC.AUBURY.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Delivering talisman";
    }

}

