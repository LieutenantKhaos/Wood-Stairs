param([string]$Root = (Join-Path $PSScriptRoot '../mod'), [string]$Destination = (Join-Path $PSScriptRoot '../mods/WoodStairs-Alternative-built-in.jar'))
$ErrorActionPreference='Stop'
Add-Type -AssemblyName System.IO.Compression
$rootPath=(Resolve-Path $Root).Path
$destinationPath=[IO.Path]::GetFullPath($Destination)
$stream=[IO.File]::Open($destinationPath,[IO.FileMode]::Create)
$zip=[IO.Compression.ZipArchive]::new($stream,[IO.Compression.ZipArchiveMode]::Create)
try{foreach($f in (Get-ChildItem $rootPath -Recurse -File)){
 $entry=$zip.CreateEntry($f.FullName.Substring($rootPath.Length+1).Replace('\','/'))
 $outputStream=$entry.Open();$inputStream=$f.OpenRead()
 try{$inputStream.CopyTo($outputStream)}finally{$inputStream.Dispose();$outputStream.Dispose()}
}}finally{$zip.Dispose();$stream.Dispose()}
Write-Output $destinationPath
