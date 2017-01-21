package org.missions.tasks.recovery_system;

import org.missions.OrionRuneMys;
import org.missions.data.enums.RM_QuestNPC;
import org.missions.data.enums.RM_QuestObject;
import org.osbot.rs07.api.model.NPC;

import viking.api.Timing;
import viking.framework.task.Task;

/**
 * Created by Sphiinx on 1/12/2017.
 */
public class RecoverTalisman extends Task<OrionRuneMys> {

    private NPC duke_horacio;

    public RecoverTalisman(OrionRuneMys mission) {
        super(mission);
    }

    @Override
    public boolean validate() {
        return configs.get(63) == 1 && !inventory.contains(RM_QuestObject.AIR_TALISMAN.getItemID());
    }

    @Override
    public void execute() {
    	if(script.BANK_CACHE.get().isEmpty() || script.BANK_CACHE.get().containsKey(RM_QuestObject.AIR_TALISMAN.getItemID()))
    		grabFromBank();
		else
		{
	        duke_horacio = npcs.closest(RM_QuestNPC.DUKE_HORACIO.getNPCArea().setPlane(1), RM_QuestNPC.DUKE_HORACIO.getNPCName());
	        if (duke_horacio != null && map.canReach(duke_horacio)) {
	            iFact.dialogue("Talk-to", RM_QuestNPC.DUKE_HORACIO.getNPCName(), 20, 1).execute();
	        } else {
	            if (walkUtils.walkToArea(RM_QuestNPC.DUKE_HORACIO.getNPCArea().setPlane(1), () -> {
	                duke_horacio = npcs.closest(RM_QuestNPC.DUKE_HORACIO.getNPCArea().setPlane(1), RM_QuestNPC.DUKE_HORACIO.getNPCName());
	                return duke_horacio != null && map.canReach(duke_horacio);
	            })) {
	                Timing.waitCondition(() -> npcs.closest(RM_QuestNPC.DUKE_HORACIO.getNPCArea().setPlane(1), RM_QuestNPC.DUKE_HORACIO.getNPCName()) != null, 150, random(2000, 2500));
	            }
	        }
		}
    }
    
    private void grabFromBank()
    {
    	if(bankUtils.isInBank())
    	{
    		if(bank.isOpen() || bankUtils.open())
    			bank.withdraw(RM_QuestObject.AIR_TALISMAN.getItemID(), 1);
    	}
    	else
    		getWalking().webWalk(bankUtils.getAllBanks(false, false));
    		
    }

    @Override
    public String toString() {
        return "Recovering talisman";
    }

}

