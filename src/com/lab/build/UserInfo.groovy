package com.lab.build

/**
 * Wrapper for getting the users login name and dev team.
 *
 * @version $Id: $
 */
class UserInfo implements Serializable {
    String devTeam
    String username

    static UserInfo fromName(String name) {
        // I believe this will only be populated if the user has logged into jenkins before.
        def user = hudson.model.User.get(name)
        if (!user) {
            return null
        }
        def groups = user.getAuthorities();
        // The ldap group is formatted as 'DevTeam-YouTeamName'
        // so we reformat and assume the slack channel will be named 'yourteamname'
        for (String group : groups) {
            // Ideally a user should not have more than 1 dev team...
            def matcher = group =~ /DevTeam-(.*)/
            if (matcher.matches()) {
                return new UserInfo(username: user.getId(), devTeam: matcher.group(1)?.toLowerCase())
            }
        }

        return new UserInfo(username: user.getId(), devTeam: "")
    }
}
