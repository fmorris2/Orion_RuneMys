package org.missions.tasks;

import org.missions.OrionRuneMys;
import org.missions.data.enums.QuestNPC;
import org.osbot.rs07.api.model.NPC;
import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/11/2017.
 */
public class FinishQuest extends Task<OrionRuneMys> {

    private NPC sedridor;

    public FinishQuest(OrionRuneMys mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(63) == 5 || configs.get(63) == 6;
    }

    @Override
    public void execute() {
        sedridor = npcs.closest(QuestNPC.SEDRIDOR.getNPCArea(), QuestNPC.SEDRIDOR.getNPCName());
        if (sedridor != null && map.canReach(sedridor)) {
            iFact.dialogue("Talk-to", QuestNPC.SEDRIDOR.getNPCName(), 20).execute();
        } else {
            if (walkUtils.walkToArea(QuestNPC.SEDRIDOR.getNPCArea(), () -> {
                sedridor = npcs.closest(QuestNPC.SEDRIDOR.getNPCArea(), QuestNPC.SEDRIDOR.getNPCName());
                return sedridor != null && sedridor.isVisible() && map.canReach(sedridor);
            })) {
                Timing.waitCondition(() -> npcs.closest(QuestNPC.SEDRIDOR.getNPCArea(), QuestNPC.SEDRIDOR.getNPCName()) != null, 150, random(2000, 2500));
            }
        }
    }

    @Override
    public String toString() {
        return "Delivering talisman";
    }

}

