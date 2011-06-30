var _FileBrowserLanguage    = 'php' ;   // asp | aspx | cfm | lasso | perl | php | py
var _QuickUploadLanguage    = 'php' ;   // asp | aspx | cfm | lasso | perl | php | py

// Don't care about the following two lines. It just calculates the correct connector
// extension to use for the default File Browser (Perl uses "cgi").
var _FileBrowserExtension = _FileBrowserLanguage == 'perl' ? 'cgi' : _FileBrowserLanguage ;
var _QuickUploadExtension = _QuickUploadLanguage == 'perl' ? 'cgi' : _QuickUploadLanguage ;

var serverUrl =  FCKConfig.BasePath.toString();
serverUrl = serverUrl.substring(0, serverUrl.indexOf('/', 8)) ;


FCKConfig.LinkBrowser = false;

FCKConfig.LinkBrowserURL = serverUrl + '/html/js/editor/fckeditor/editor/filemanager/browser/liferay/browser.html?Connector=' + "/c/portal/fckeditor";
FCKConfig.LinkBrowserWindowWidth    = FCKConfig.ScreenWidth * 0.7 ;     // 70%
FCKConfig.LinkBrowserWindowHeight   = FCKConfig.ScreenHeight * 0.7 ;    // 70%

FCKConfig.ImageBrowser = true ;
FCKConfig.ImageBrowserURL = serverUrl + '/html/js/editor/fckeditor/editor/filemanager/browser/liferay/browser.html?Type=Image&Connector=' + "/c/portal/fckeditor";
FCKConfig.ImageBrowserWindowWidth  = FCKConfig.ScreenWidth * 0.7 ;  // 70% ;
FCKConfig.ImageBrowserWindowHeight = FCKConfig.ScreenHeight * 0.7 ; // 70% ;

FCKConfig.ToolbarSets['colabTab'] = [['Bold','Italic','Underline', 'Style' , '-', 'JustifyLeft','JustifyCenter','JustifyRight', '-', 'Table', 'Image', 'Link', 'SpecialChar', 'Source', 'PasteWord']];
FCKConfig.ForcePasteAsPlainText = false;
FCKConfig.CleanWordKeepsStructure = true ;
FCKConfig.CustomStyles = { };
FCKConfig.StylesXmlPath = '../../../../js/fckstyles.xml' ;
FCKConfig.EditorAreaCSS = '../../../../../collaboratorium-theme/css/fckeditor_styles.css' ;
FCKConfig.BodyId = 'main';