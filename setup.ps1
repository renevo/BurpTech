## Environmental Setup Script ##
$forgeVersion = "7.8.1.737"
$mcVersion = "1.5.2"
$mcpVersion = "751"

$rootLocation = get-location
$rootPath = $rootLocation.path

write-host "Cleaning Environment"

### CLEAN ###
if (Test-Path .\forgeinstall.log)
{
    Remove-Item .\forgeinstall.log -Force
}

if (Test-Path .\forgeinstall-errors.log)
{
    Remove-Item .\forgeinstall-errors.log -Force
}

if (Test-Path .\downloads)
{
    Remove-Item .\downloads -Force -Recurse
}
New-Item -ItemType Directory -Force -Path .\downloads


if (Test-Path .\mcp)
{
    Remove-Item .\mcp -Force -Recurse
}
New-Item -ItemType Directory -Force -Path .\mcp


### DOWNLOAD FILES ###
write-host "Downloading Files"

$webclient = New-Object System.Net.WebClient

write-host "Downloading forge $forgeVersion"
$webclient.DownloadFile("http://files.minecraftforge.net/minecraftforge/minecraftforge-src-$mcVersion-$forgeVersion.zip", ".\downloads\minecraftforge-src-$mcVersion-$forgeVersion.zip")

write-host "Downloading mcp $mcpVersion"
$webclient.DownloadFile("http://mcp.ocean-labs.de/files/archive/mcp$mcpVersion.zip", ".\downloads\mcp$mcpVersion.zip")


### EXTRACT FILES ###
write-host "Extracting Files"

$shell=new-object -com shell.application

$Output = $shell.namespace("$rootPath\mcp")

$ZipFiles = get-childitem downloads/*.zip

foreach ($ZipFile in $ZipFiles)
{
    write-host "Extracting $ZipFile"
    
    $ZipFolder = $shell.namespace($ZipFile.fullname)
    
    # 0x4 will hide the UI 0x10 will overwrite files, 0x14 will do both
    $Output.Copyhere($ZipFolder.items(), 0x14) 
}


## INSTALL FORGE ##
write-host "Installing Forge"

start-process -NoNewWindow -Wait -FilePath:"$rootPath\mcp\forge\fml\python\python_fml.exe" -WorkingDirectory:"$rootPath\mcp\forge\" -RedirectStandardOutput:"$rootPath\forgeinstall.log" -RedirectStandardError:"$rootPath\forgeinstall-errors.log" -ArgumentList "install.py"

## DONE ##
write-host "Work Complete"