# Release 3: Final release 

## Introduction 

For the third release we will present the final product of our Cookbook. The focus for this release has been to set up a REST API and to add some new functionality. The majority of our time has been used on implementing the REST API. None of the group members had any prior knowledge to this, so it did take some time to implement. We also made our app shippable.

**New functionality:**  
The user is now also able to get recipes from the cookbook based on the ingredients they already have at home. See [UserStories.md](/docs/release3/UserStories.md) for description. 

**RestAPI:**  
For detailed description of REST API go to [README for REST API](/cookbook/restserver/README.md).

**Shippable Product:**  
We used JLink and JPackage to make our application a shippable product. To do this we followed this [guide](https://dev.to/cherrychain/javafx-jlink-and-jpackage-h9) by Luca Guadagnini. Further down is a step-by-step guide for how to download and launch the application using JLink and JPackage. 



## Diagrams 
For an overview and explanation of the diagrams go to [README](/docs/release3/Diagrams/README.md) for diagrams in the [Diagrams](/docs/release3/Diagrams) folder.

## Work habits and workflow

**From release 2**:   
We have continued to focus on the aspects from release 2:  
**Pair Programming, Gitlab issues, Branch-Based Development, Active Group Discussions and Milestones**.  
See [README](/docs/release2/README.md) from release 2 for elaboration. 

**Connecting branch to an issue**:  
During this final release we have focused on making a branch for every issue, and naming it with the number for the issue. This is so that the branch automatically closes when we merge it back into master.

## Quality assurance of code
We have continued to use **Jacoco**, **Spotbugs** and **Checkstyle** for quality assurance of the code.
For release 3 the group has been proofreading each others code. This is to check if any methodes can be written more clearly and efficient. We have also tested edge cases, and checked for vulnerabilities in the application.  


## Reflection and retrospective discussion following the final release

After this final release we will discuss what went well, and what issues we faced along the way. 

### Work habits and workflow
Our group has developed good workflow and work habits throughout this project. We will now discuss how we have done it and what we have learned.  

1. **Good Work Habits:** The group has had good work habits from the very beginning of the project. These habits include a commitment to punctuality, clear communication, and a strong work ethic. 
2. **Regular Meetings:** We understand the importance of effective communication and alignment within the team. To achieve this, we conduct regular meetings that are strategically planned. Our weekly schedule includes two meetings:
    - **Shorter Meeting (2 hours):** We kick off the week with a shorter, two-hour meeting. This initial meeting serves as a touchpoint for the team, allowing us to set our goals and objectives for the week ahead. It provides a structured start to our work, giving us a clear direction.
    - **Longer Meeting (4 hours):** Following the shorter meeting, we allocate time to work on  tasks in pairs throughout the week. Mid-week, we reconvene for a longer, four-hour meeting. This extended meeting allows us to delve deeper into our projects, address challenges, and fine-tune our work. By separating these meetings, we can maintain an efficient workflow while minimizing distractions.
3. **Branch-Based Development:** To ensure a smooth and conflict-free development process, we have adopted branch-based development. Each branch is directly linked to a specific issue or task, enabling us to work in a focused manner. This practice significantly reduces merge conflicts and provides clarity on what each team member needs to work on, streamlining our development process. This is something we did not use in release 1, that made it difficult understad what was worked on in each branch.  
4. **Evening Workshops:** We understand that tackling tedious issues can sometimes lead to a dip in motivation. To combat this, we've introduced evening workshops where we not only work together but also prepare and enjoy dinner as a team. This communal activity helps boost morale and maintain our enthusiasm for the tasks at hand.
5. **Stand-Up:** Before each meeting, we conduct a stand-up session to review what each team member has accomplished. This brief update session provides us with an overview of our progress and helps us stay informed about the status of ongoing projects.

By adhering to these work habits and following our structured workflow, we've achieved a harmonious and productive work environment. This approach allows us to maintain focus, collaboration, and motivation, ultimately contributing to our success as a team.

### Documentation
We have written documentation for every release. Each release contains a README.md file. The README files contains a description of changes, and what we have done during that release. We have also written userstories for the functionalities, and added pictures of prototypes. We could also have added any problems we had or things that did not go so well in the README.md files. 

### Quality assurance   
The group got better at reading through each others code during the final release. This should have been done from the beginning to ensure better code quality.
We are happy with the help we got from using **Jacoco**, **Spotbugs** and **Checkstyle**. This is something we actively used from the beginning.   
## Conclusion
The project has made all group members gain a lot of experience, developed our team-management and new personal skills.   
