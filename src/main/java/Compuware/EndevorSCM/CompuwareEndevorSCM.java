package Compuware.EndevorSCM;
import hudson.Launcher;
import hudson.Extension;
import hudson.scm.*;
import java.io.*;
import hudson.FilePath;
import hudson.util.FormValidation;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;
import java.util.List;
import java.util.ArrayList;
import hudson.util.ListBoxModel;
import Compuware.EndevorSCM.WorkspaceUpdaterDescriptor;

import javax.servlet.ServletException;
import java.io.IOException;

public class CompuwareEndevorSCM extends SCM {

    private final String hostport;
    private final String filterPattern;
    private WorkspaceUpdater workspaceUpdater;

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public CompuwareEndevorSCM(String hostport, String filterPattern, WorkspaceUpdater workspaceUpdater)
    {
        this.hostport = hostport;
        this.filterPattern = filterPattern;
        this.workspaceUpdater = workspaceUpdater;
    }

    //Abstract method from SCM class, not exactly sure what it does
    @Override
    public ChangeLogParser createChangeLogParser() {
        return null; 
    }

    //This method is called first when a Build is run on a job
    @Override
    public boolean checkout(AbstractBuild<?, ?> build, Launcher launcher, FilePath workspaceFilePath, BuildListener listener, File changelogFile) throws IOException, InterruptedException{    
        listener.getLogger().println("Hostport = "+ getHostport());
        listener.getLogger().println("Filter Pattern = "+ getFilterPattern());

        if (getWorkspaceUpdater() instanceof UpdateUpdater)
        {
            listener.getLogger().println("Checkout Strategy = 1");
        }
        else if (getWorkspaceUpdater() instanceof FreshUpdater)
        {
            listener.getLogger().println("Checkout Strategy = 2");
        }
        else if (getWorkspaceUpdater() instanceof EmulateUpdater)
        {
            listener.getLogger().println("Checkout Strategy = 3");
        }
        else
        {
            listener.getLogger().println("Checkout Strategy = 4");
        }

        return true;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }

    public String getHostport() {
        return hostport;
    }

    public String getFilterPattern() {
        return filterPattern;
    }

    public WorkspaceUpdater getWorkspaceUpdater(){
        return workspaceUpdater;
    }

    @Extension
    public static class DescriptorImpl extends SCMDescriptor<CompuwareEndevorSCM>{

        public String description;

        public DescriptorImpl() {
            super(CompuwareEndevorSCM.class, null);
            load();
        }

        @Override
        public String getDisplayName()
        {
            return "Endevor";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException
        {
            return super.configure(req,formData);
        }

        /*
        public ListBoxModel doFillCheckoutItems(@QueryParameter String checkout) {

            //Fill 'Check-out Strategy' selection box. Format (DisplayName, Value)
            ListBoxModel m = new ListBoxModel();
            m.add("Use 'update' as much as possible", "1");
            m.add("Always check out a fresh copy", "2");
            m.add("Emulate clean checkout by first deleting unversioned/ignored files, then 'update'", "3");
            m.add("Use 'update' as much as possible, with 'revert' before update", "4");

            description = checkout;
            return m;
        }
        */

        public FormValidation doCheckHostport(@QueryParameter String value) throws IOException, ServletException
        {
            if (value != "")
            {
                return FormValidation.ok();
            }
            else
            {
                return FormValidation.error("Host:Port must be defined.");
            }
        }

        public FormValidation doCheckFilterPattern(@QueryParameter String value) throws IOException, ServletException
        {
            if (value != "")
            {
                return FormValidation.ok();
            }
            else
            {
                return FormValidation.error("Filter pattern must be defined.");
            }
        }

        //Method called by config.jelly to populate the 'Checkout Strategy' selection box
        public List<WorkspaceUpdaterDescriptor> getWorkspaceUpdaterDescriptors() {
            return WorkspaceUpdaterDescriptor.all();
        }
    }
}

