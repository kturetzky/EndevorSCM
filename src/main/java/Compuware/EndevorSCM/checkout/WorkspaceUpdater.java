package Compuware.EndevorSCM;

import hudson.ExtensionPoint;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Describable;
import hudson.model.TaskListener;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Encapsulates the logic of how files are obtained from a repository.
 *
**/

public abstract class WorkspaceUpdater extends AbstractDescribableImpl<WorkspaceUpdater> implements ExtensionPoint{

    @Override
    public WorkspaceUpdaterDescriptor getDescriptor() {
        return (WorkspaceUpdaterDescriptor)super.getDescriptor();
    }
}