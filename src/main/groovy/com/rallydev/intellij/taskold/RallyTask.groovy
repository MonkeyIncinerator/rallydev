package com.rallydev.intellij.taskold

import com.intellij.tasks.Comment
import com.intellij.tasks.Task
import com.intellij.tasks.TaskType
import org.sbelei.rally.domain.BasicEntity

import javax.swing.*

class RallyTask extends Task {

    String id
    String summary
    String description
    Date updated
    Date created
    boolean closed
    String issueUrl
    TaskType type = TaskType.OTHER
    public _Dummy_ ( ) {
    }

    public RallyTask(BasicEntity entity) {
        id = entity.id;
        description = entity.toString();
        summary = entity.name;
        issueUrl = entity.ref;
    }

    @Override
    Comment[] getComments() {
        return new Comment[0]
    }

    @Override
    Icon getIcon() {
        return null
    }

    boolean isIssue() {
        return true
    }

}
