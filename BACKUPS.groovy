pipeline {
    agent any

    stages {
        stage('Check and Create Folders') {
            steps {
                script {
                    // Define the path to the BACKUPS and key folders
                    def backupsPath = 'D:/BACKUPS'
                    def keyPath = "${backupsPath}/key"

                    // Check if the BACKUPS folder exists
                    def backupsFolder = new File(backupsPath)

                    if (!backupsFolder.exists()) {
                        // Create the BACKUPS folder if it doesn't exist
                        backupsFolder.mkdirs()
                        echo "Created BACKUPS folder at ${backupsPath}"
                    } else {
                        echo "BACKUPS folder already exists at ${backupsPath}"
                    }

                    // Check if the key folder exists
                    def keyFolder = new File(keyPath)

                    if (!keyFolder.exists()) {
                        // Create the key folder if it doesn't exist
                        keyFolder.mkdirs()
                        echo "Created key folder at ${keyPath}"
                    } else {
                        echo "key folder already exists at ${keyPath}"
                    }
                }
            }
        }
    }
}




pipeline {
    agent any

    stages {
        stage('Create BACKUPS and key folders') {
            steps {
                script {
                    // Check if D:\BACKUPS exists and create it if not
                    bat '''
                        IF NOT EXIST D:\\BACKUPS (
                            ECHO Creating D:\\BACKUPS folder...
                            MKDIR D:\\BACKUPS
                        ) ELSE (
                            ECHO D:\\BACKUPS folder already exists.
                        )
                    '''

                    // Create D:\BACKUPS\key folder
                    bat '''
                        IF NOT EXIST D:\\BACKUPS\\key (
                            ECHO Creating D:\\BACKUPS\\key folder...
                            MKDIR D:\\BACKUPS\\key
                        ) ELSE (
                            ECHO D:\\BACKUPS\\key folder already exists.
                        )
                    '''
                }
            }
        }
    }
}