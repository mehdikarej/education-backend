package crocoder.webteam.edukate.services;

import crocoder.webteam.edukate.models.User;

public interface AuthService {
	 User getCurrentUser();
	 Long getCurrentUserId();
}
