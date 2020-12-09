package com.wangjg.gitlabapi;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.Pager;
import org.gitlab4j.api.models.Project;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class GitlabApiTest {

    private String personal_access_token = "h_yzDovWybyeKWqgpMZ9";

    private String gitlab_url = "https://gitlab.iquantex.com";

    private GitLabApi gitlabApi;

    {
        gitlabApi = new GitLabApi(gitlab_url, personal_access_token);
        // Log using the shared logger and default level of FINE
        gitlabApi.enableRequestResponseLogging();
        // Log using the shared logger and the INFO level
        gitlabApi.enableRequestResponseLogging(java.util.logging.Level.INFO);
        gitlabApi.setIgnoreCertificateErrors(true);
    }

    public List<Project> listProject() throws GitLabApiException {
        // Get a Pager instance that will page through the projects with 10 projects per page
        Pager<Project> projectPager = gitlabApi.getProjectApi().getProjects(10);
        List<Project> list = new ArrayList<>();
        // Iterate through the pages and print out the name and description
        while (projectPager.hasNext()) {
            for (Project project : projectPager.next()) {
                System.out.println(project.getName() + " -: " + project.getDescription());
                list.add(project);
            }
        }
        return list;
    }

    public static void main(String[] args) throws GitLabApiException {
        GitlabApiTest test = new GitlabApiTest();
        test.listProject();
    }
}
