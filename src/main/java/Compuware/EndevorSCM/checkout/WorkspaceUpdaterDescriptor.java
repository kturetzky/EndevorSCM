package Compuware.EndevorSCM;

import hudson.DescriptorExtensionList;
import hudson.model.Descriptor;
import hudson.model.Hudson;

public abstract class WorkspaceUpdaterDescriptor extends Descriptor<WorkspaceUpdater> {

    public static DescriptorExtensionList<WorkspaceUpdater,WorkspaceUpdaterDescriptor> all() {
        return Hudson.getInstance().<WorkspaceUpdater,WorkspaceUpdaterDescriptor>getDescriptorList(WorkspaceUpdater.class);
    }
}