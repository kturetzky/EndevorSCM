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

import javax.servlet.ServletException;
import java.io.IOException;

public class RevertUpdater extends WorkspaceUpdater {
    
    @DataBoundConstructor
    public RevertUpdater() {
    }

    @Extension
    public static class DescriptorImpl extends WorkspaceUpdaterDescriptor {
        @Override
        public String getDisplayName() {
            return "Use 'update' as much as possible, with 'revert' before update";
        }
    }
}