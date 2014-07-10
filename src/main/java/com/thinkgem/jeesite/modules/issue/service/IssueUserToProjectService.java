package com.thinkgem.jeesite.modules.issue.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Parameter;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.issue.dao.UserToProjectDao;
import com.thinkgem.jeesite.modules.issue.entity.IssueProject;
import com.thinkgem.jeesite.modules.issue.entity.UserToProject;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

@Service
@Transactional(readOnly = true)
public class IssueUserToProjectService extends BaseService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserToProjectDao userToProjectDao;

	public List<UserToProject> getUsersByProject(IssueProject project) {
		DetachedCriteria dc = userToProjectDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(project.getId())) {
			dc.createAlias("project", "project");
			dc.add(Restrictions.eq("project.id", project.getId()));
		}
		return userToProjectDao.find(dc);
	}

	@Transactional(readOnly = false)
	public void save(UserToProject userToProject) {
		userToProjectDao.save(userToProject);
	}
	
	@Transactional(readOnly = false)
	public void saveUserToProject(IssueProject issueProject,
			List<String> userIdList) {
		
		userToProjectDao.updateBySql("delete from t_user_to_project where project_id=:p1 and office_id=:p2", new Parameter(issueProject.getId(),issueProject.getSearch().getId()));
		
		List<UserToProject> usrProjects = new ArrayList<UserToProject>();
		for (String userId : userIdList) {
			UserToProject userToProject = new UserToProject();
			User user = userDao.get(userId);
			userToProject.setUser(user);
			userToProject.setOffice(issueProject.getSearch());
			userToProject.setProject(issueProject);
			usrProjects.add(userToProject);
		}
		
		userToProjectDao.save(usrProjects);
	}

	@Transactional(readOnly = false)
	public void deleteUserToProject(String id) {
		userToProjectDao.deleteById(id);
	}

}
