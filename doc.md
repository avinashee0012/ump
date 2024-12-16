## Spring Boot User Registration and Login Example Tutorial

##### LINK: https://www.javaguides.net/2018/10/user-registration-module-using-springboot-springmvc-springsecurity-hibernate5-thymeleaf-mysql.html
_____

# User Management Portal

### Tools and technologies:
##### - Spring Boot 3
##### - Spring MVC 6
##### - Spring Security 6
##### - Hibernate 6
##### - Thymeleaf 3
##### - MySQL 8
##### - Maven
_____

### Functionalities:
##### 1. Register a user (stored data in MySQL database).
##### 2. Login Authentication - validate user login credentials with database email and password.
##### 3. We will secure the Registered Users Page with role-based Spring Security.
_____

### Dependencies:
##### Web, JPA, MySQL, Thymeleaf, Security, Lombok
_____

### Steps:
##### 1. Create a Spring Boot Project with dependencies
##### 2. Create a Project Structure or Packing Structure
##### 3. Configure MySQL database
##### 4. Create JPA Entities - User and Role
##### 5. Create UserRepository and RoleRepository
##### 6. Create a Thymeleaf Template for the Home Page
##### 7. Create a Service Layer
##### 8. Create UserDto Model Class
##### 9. User Registration Feature Implementation
##### 10. Display List Registered Users
##### 11. Create a Custom Login Form 
##### 12. Configure Spring Security
##### 13. Database Authentication Implementation
##### 14. Demo
##### 15. Upload Source Code on GitHub

###### Setting-up git and github:
##### > Install latest version of git on your computer.

##### > For all repos on your computer:
#####    git config --global user.name "Rebellion"
#####    git config --global user.name
#####    git config --global user.email "avinashee0012@gmail.com"
#####    git config --global user.email

##### > For a single repo on your computer (Change to the directory):
#####    git config user.name "Rebellion"
#####    git config user.name
#####    git config user.email "avinashee0012@gmail.com"
#####    git config user.email

##### > To access GitHub using SSH:
#####    Create an SSH key pair on your computer:
#####        ssh-keygen -t rsa -b 4096 -C "avinashee0012@gmail.com"
#####    Run ssh-agent:
#####        Get-Service -Name ssh-agent | Set-Service -StartupType Manual
#####        Start-Service ssh-agent
#####        Get-Service -Name ssh-agent
#####    Copy the public SSH key to your GitHub account
#####        Visit https://github.com/settings/keys
#####        Add public SSH Key there.
#####    Test github connection:
#####        ssh -T git@github.com

##### > Pushing repo to GitHub:
#####    Create an empty directory.
#####    Convert it into git directory: 
#####        git init
#####    Create a sample file:
#####        echo "This is a sample file." > sample_file.md
#####    Commit and push to GitHub:
#####        git status
#####        git add .
#####        git status
#####        git branch -M main
#####        git commit -m "first commit"
#####        git remote add origin git@github.com:avinashee0012/ump.git
#####        git push -u origin main
    



