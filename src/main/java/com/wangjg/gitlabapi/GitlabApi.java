package com.wangjg.gitlabapi;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.Pager;
import org.gitlab4j.api.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings("FieldCanBeLocal")
public class GitlabApi {

    private String personal_access_token = "-N9nzmUg_BUqypczgVgF";

    private String gitlab_url = "https://gitlab-cicc.iquantex.com";

    private GitLabApi gitlabApi;

    {
        gitlabApi = new GitLabApi(gitlab_url, personal_access_token);
        // Log using the shared logger and default level of FINE
        gitlabApi.enableRequestResponseLogging();
        // Log using the shared logger and the INFO level
        gitlabApi.enableRequestResponseLogging(java.util.logging.Level.INFO);
        gitlabApi.setIgnoreCertificateErrors(true);
    }

    public <Return> Return exec(Function<GitLabApi, Return> function) {
        return function.apply(gitlabApi);
    }

    public Group createGroup(GroupParams groupParams) throws GitLabApiException {
        return exec(gitLabApi -> {
            try {
                return gitlabApi.getGroupApi().createGroup(groupParams);
            } catch (GitLabApiException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public List<Project> listProjectByGroup(String groupIdOrPath) throws GitLabApiException {
        return gitlabApi.getGroupApi().getProjects(groupIdOrPath);
    }

    public List<Group> listGroup() throws GitLabApiException {
        Pager<Group> groupPager = gitlabApi.getGroupApi().getGroups(10);
        List<Group> list = new ArrayList<>();
        while (groupPager.hasNext()) {
            list.addAll(groupPager.next());
        }
        return list;
    }

    public Epic createEpic(String groupIdOrPath, Epic epic) throws GitLabApiException {
        return gitlabApi.getEpicsApi().createEpic(groupIdOrPath, epic);
    }

    public List<Epic> listEpic(String groupIdOrPath) throws GitLabApiException {
        return gitlabApi.getEpicsApi().getEpics(groupIdOrPath);
    }

    public List<Issue> listIssue() throws GitLabApiException {
        return gitlabApi.getIssuesApi().getIssues();
    }


    public static void main(String[] args) throws GitLabApiException {
        GitlabApi test = new GitlabApi();
//        List<Group> groups = test.listGroup();
//        List<Project> projects = test.listProjectByGroup("just4test");
        List<Issue> issues = test.listIssue();
//        System.out.println(groups);
//        System.out.println(projects);
        System.out.println(issues);
    }
}
