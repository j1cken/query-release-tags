package it.targz.restclient;

public class GithubProject {
    final String shortname;
    final String type;
    final boolean isTag;
    final String owner;
    final String project_name;

    public GithubProject(String shortname, String type, String istag, String owner, String project_name) {
        this.shortname = shortname;
        this.type = type;
        this.isTag = "tag".equals(istag);
        this.owner = owner;
        this.project_name = project_name;
    }

    
	public String getOwner() {
		return owner;
	}

	public String getProjectName() {
		return project_name;
	}

    public String getShortname() {
        return shortname;
    }

    public String getPlatform() {
        return type;
    }

    public boolean isTag() {
        return isTag;
    }

}