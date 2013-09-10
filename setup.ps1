## Environmental Setup Script ##
$forgeVersion = "9.10.0.804"
$mcVersion = "1.6.2"

$rootLocation = get-location
$rootPath = $rootLocation.path

## TODO: Add the Git commands here to ignore /elipse/ file changes

### CREATE DOWNLOADS DIR ###
if ((Test-Path .\downloads) -eq $False)
{
	New-Item -ItemType Directory -Force -Path .\downloads
}

if ((Test-Path .\downloads\minecraftforge-src-$mcVersion-$forgeVersion.zip) -eq $False)
{
    Remove-Item .\forge -Force -Recurse
	
	### DOWNLOAD FILES ###
	write-host "Downloading Files"

	$webclient = New-Object System.Net.WebClient

	write-host "Downloading forge $forgeVersion"
	$webclient.DownloadFile("http://files.minecraftforge.net/minecraftforge/minecraftforge-src-$mcVersion-$forgeVersion.zip", ".\downloads\minecraftforge-src-$mcVersion-$forgeVersion.zip")

	### EXTRACT FILES ###
	write-host "Extracting Files"

	$shell=new-object -com shell.application

	$Output = $shell.namespace("$rootPath")

	write-host "Extracting $ZipFile"

	$ZipFolder = $shell.namespace(".\downloads\minecraftforge-src-$mcVersion-$forgeVersion.zip")

	# 0x4 will hide the UI 0x10 will overwrite files, 0x14 will do both
	$Output.Copyhere($ZipFolder.items(), 0x14) 

	## INSTALL FORGE ##
	write-host "Installing Forge"

	start-process -Wait -FilePath:"$rootPath\forge\fml\python\python_fml.exe" -WorkingDirectory:"$rootPath\forge\" -ArgumentList "install.py"
}



## DONE ##
write-host "Work Complete"