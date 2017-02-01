package org.missions;

import org.missions.tasks.*;
import org.missions.tasks.recovery_system.RecoverBox;
import org.missions.tasks.recovery_system.RecoverNote;
import org.missions.tasks.recovery_system.RecoverTalisman;
import viking.framework.goal.GoalList;
import viking.framework.goal.impl.InfiniteGoal;
import viking.framework.mission.Mission;
import viking.framework.script.VikingScript;
import viking.framework.task.TaskManager;

public class OrionRuneMys extends Mission {

    private final TaskManager<OrionRuneMys> TASK_MANAGER = new TaskManager<>(this);

    public OrionRuneMys(VikingScript script) {
        super(script);
    }

    @Override
    public boolean canEnd() {
        return questing.closeQuestCompletion() && configs.get(63) == 6;
    }

    @Override
    public String getMissionName() {
        return "Orion Rune Mysteries";
    }

    @Override
    public String getCurrentTaskName() {
        return TASK_MANAGER.getStatus();
    }

    @Override
    public String getEndMessage() {
        return null;
    }

    @Override
    public GoalList getGoals() {
        return new GoalList(new InfiniteGoal());
    }

    @Override
    public String[] getMissionPaint() {
        return null;
    }

    @Override
    public int execute() {
        TASK_MANAGER.loop();
        return 150;
    }

    @Override
    public void onMissionStart() {
        TASK_MANAGER.addTask(new RM_DepositItems(this), new RecoverTalisman(this), new RM_StartQuest(this), new DeliverTalisman(this), new RecoverBox(this), new DeliverBox(this), new RecoverNote(this), new RM_FinishQuest(this));
    }

    @Override
    public void resetPaint() {
    }

}
