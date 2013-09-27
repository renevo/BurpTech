## Environmental Setup Script ##
$forgeVersion = "9.11.0.897"
$mcVersion = "1.6.4"

$rootLocation = get-location
$rootPath = $rootLocation.path

$forgeFile = $rootPath + "\downloads\minecraftforge-src-$mcVersion-$forgeVersion.zip"
Write-Host $forgeFile

### CREATE DOWNLOADS DIR ###
if ((Test-Path .\downloads) -eq $False)
{
	New-Item -ItemType Directory -Force -Path .\downloads
}

if ((Test-Path .\downloads\minecraftforge-src-$mcVersion-$forgeVersion.zip) -eq $False)
{
	### DOWNLOAD FILES ###
	write-host "Downloading Files"

	$webclient = New-Object System.Net.WebClient

	write-host "Downloading forge $forgeVersion"
	$webclient.DownloadFile("http://files.minecraftforge.net/minecraftforge/minecraftforge-src-$mcVersion-$forgeVersion.zip", $forgeFile)
}

if (Test-Path .\forge)
{
    Remove-Item .\forge -Force -Recurse
}
	
### EXTRACT FILES ###
$shell=new-object -com shell.application

$Output = $shell.namespace($rootPath)

$ZipFolder = $shell.namespace($forgeFile)

Write-Host Extracting $forgeFile to $rootPath

# 0x4 will hide the UI 0x10 will overwrite files, 0x14 will do both
$Output.Copyhere($ZipFolder.items(), 0x14) 

## INSTALL FORGE ##
write-host "Installing Forge"

start-process -Wait -FilePath:"$rootPath\forge\fml\python\python_fml.exe" -WorkingDirectory:"$rootPath\forge\" -ArgumentList "install.py"

## DONE ##
write-host "Work Complete"