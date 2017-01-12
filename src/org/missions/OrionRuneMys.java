package org.missions;

import org.missions.tasks.DeliverBox;
import org.missions.tasks.DeliverTalisman;
import org.missions.tasks.FinishQuest;
import org.missions.tasks.StartQuest;
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
        return configs.get(63) == 6;
    }

    @Override
    public String getMissionName() {
        return null;
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
        TASK_MANAGER.addTask(new StartQuest(this), new DeliverTalisman(this), new DeliverBox(this), new FinishQuest(this));
    }

    @Override
    public void resetPaint() {
    }

}
