--- Amazon-Linux (YUM) ---
0a. Install NodeJS (not Node)
0b. Install MySQL
1a. git clone facial-server
1a. git clone facial
1. Initialize MySQL database
  a.  Start the service: mysqld if not already started
  b.  Create database named facial
  c.  Import 'Stars.sql' and 'Images.sql' table
2. Install Maven
  sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
  sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
  sudo yum install -y apache-maven
  mvn --version
3. Compile project using: "mvn compile"
4. Start the server: mvn exec:java -Dexec.mainClass="com.example.Main"
  - You mave have to run this twice the first time



We just changed the Redirect URL of login() to be the private IP:
  - Private IP: grep nameserver /etc/resolv.conf
  - 
