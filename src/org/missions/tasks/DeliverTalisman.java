package org.missions.tasks;

import org.missions.OrionRuneMys;
import org.missions.data.enums.RM_QuestNPC;
import org.missions.data.enums.RM_QuestObject;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class DeliverTalisman extends Task<OrionRuneMys> {

    private NPC sedridor;

    public DeliverTalisman(OrionRuneMys mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return (configs.get(63) == 1 && inventory.contains(RM_QuestObject.AIR_TALISMAN.getItemID()) || configs.get(63) == 2);
    }

    @Override
    public void execute() {
        sedridor = npcs.closest(RM_QuestNPC.SEDRIDOR.getNPCArea(), RM_QuestNPC.SEDRIDOR.getNPCName());
        if (sedridor != null && map.canReach(sedridor)) {
            iFact.dialogue("Talk-to", RM_QuestNPC.SEDRIDOR.getNPCName(), 20, 3, 1).execute();
        } else {
            if (walkUtils.walkToArea(RM_QuestNPC.SEDRIDOR.getNPCArea(), () -> {
                sedridor = npcs.closest(RM_QuestNPC.SEDRIDOR.getNPCArea(), RM_QuestNPC.SEDRIDOR.getNPCName());
                return sedridor != null && map.canReach(sedridor);
            })) {
                Timing.waitCondition(() -> npcs.closest(RM_QuestNPC.SEDRIDOR.getNPCArea(), RM_QuestNPC.SEDRIDOR.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Delivering talisman";
    }

}

