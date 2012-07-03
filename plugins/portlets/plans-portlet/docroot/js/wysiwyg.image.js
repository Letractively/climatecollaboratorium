/**
 * Controls: Image plugin
 *
 * Depends on jWYSIWYG
 */
(function ($) {
	"use strict";

	if (undefined === $.wysiwyg) {
		throw "wysiwyg.image.js depends on $.wysiwyg";
	}

	if (!$.wysiwyg.controls) {
		$.wysiwyg.controls = {};
	}

	/*
	 * Wysiwyg namespace: public properties and methods
	 */
	$.wysiwyg.controls.image = {
		groupIndex: 6,
		visible: true,
		exec: function () {
			$.wysiwyg.controls.image.init(this);
		},
		tags: ["img"],
		tooltip: "Insert image",
		init: function (Wysiwyg) {
			var self = this, elements, adialog, dialog, formImageHtml, regexp, dialogReplacements, key, translation,
				img = {
					alt: "",
					self: Wysiwyg.dom ? Wysiwyg.dom.getElement("img") : null, // link to element node
					src: "http://",
					title: ""
				};

			dialogReplacements = {
				legend	: "Insert Image",
				preview : "Preview",
				url     : "URL",
				title   : "Title",
				description : "Description",
				width   : "Width",
				height  : "Height",
				original : "Original W x H",
				"float"	: "Float",
				floatNone : "None",
				floatLeft : "Left",
				floatRight : "Right",
				submit  : "Insert Image",
				changeImage  : "Change Image",
				reset   : "Cancel",
				fileManagerIcon : "Select file from server"
			};
			
			if (img.self) {
				dialogReplacements.submit = dialogReplacements.changeImage;
			}

			formImageHtml = '<form class="wysiwyg" id="wysiwyg-addImage">' +
				'<div class="form-row"><label for="name">{url}:</label><div class="form-row-value"><input class="imageUrl" type="text" name="src" value=""/>';

			if ($.wysiwyg.fileManager && $.wysiwyg.fileManager.ready) {
				// Add the File Manager icon:
				formImageHtml += '<div class="wysiwyg-fileManager" title="{fileManagerIcon}"/>';
			}

			formImageHtml += '</div><div class="clear"></div></div>' +
				'<div class="form-row" style="display: none;"><label for="name">{title}:</label><div class="form-row-value"><input type="text" name="imgtitle" value=""/></div> <div class="clear"></div></div>' +
				'<div class="form-row" style="display: none;"><label for="name">{description}:</label><div class="form-row-value"><input type="text" name="description" value=""/></div> <div class="clear"></div></div>' +
				'<div class="form-row"><label for="name">Percent of original size:</label><div class="form-row-value"><input type="text" name="percentOfSize" class="percentOfSize"  value="100" /> %</div> <div class="clear"></div></div>' +
				'<div class="form-row form-row-last"><label for="name"></label>' +
				'<div class="form-row-value">' +
				'<div class="blue-button"><a href="javascript:;" onclick=\"jQuery(this).parents(\'form\').submit();\">{submit}</a></div>' +
				'<div class="blue-button"><a class="resetForm" href="javascript:;" >{reset}</a></div></div> <div class="clear"></div></div></form>';

			for (key in dialogReplacements) {
				if ($.wysiwyg.i18n) {
					translation = $.wysiwyg.i18n.t(dialogReplacements[key], "dialogs.image");

					if (translation === dialogReplacements[key]) { // if not translated search in dialogs 
						translation = $.wysiwyg.i18n.t(dialogReplacements[key], "dialogs");
					}

					dialogReplacements[key] = translation;
				}

				regexp = new RegExp("{" + key + "}", "g");
				formImageHtml = formImageHtml.replace(regexp, dialogReplacements[key]);
			}

			if (img.self) {
				img.src    = img.self.src    ? img.self.src    : "";
				img.alt    = img.self.alt    ? img.self.alt    : "";
				img.title  = img.self.title  ? img.self.title  : "";
				img.width  = img.self.width  ? img.self.width  : "";
				img.height = img.self.height ? img.self.height : "";
				img.percentOfSize = img.self.style.width ? $(img.self).css("width").replace("%", "") : "";
				img.styleFloat = $(img.self).css("float");
				
			}

			adialog = new $.wysiwyg.dialog(Wysiwyg, {
				"title"   : dialogReplacements.legend,
				"height"  : 264,
				"content" : formImageHtml
			});

			$(adialog).bind("afterOpen", function (e, dialog) {
				dialog.find("form#wysiwyg-addImage").submit(function (e) {
					e.preventDefault();
					self.processInsert(dialog, Wysiwyg, img);

					adialog.close();
					return false;
				});

				// File Manager (select file):
				if ($.wysiwyg.fileManager) {
					$("div.wysiwyg-fileManager").bind("click", function () {
						$.wysiwyg.fileManager.init(function (selected) {
							dialog.find("input[name=src]").val(selected);
							dialog.find("input[name=src]").trigger("change");
						});
					});
				}

				$(".resetForm", dialog).click(function (e) {
					adialog.close();

					return false;
				});

				$("fieldset", dialog).click(function (e) {
					e.stopPropagation();
				});

				self.makeForm(dialog, img);
			});

			adialog.open();

			$(Wysiwyg.editorDoc).trigger("editorRefresh.wysiwyg");
		},

		processInsert: function (context, Wysiwyg, img) {
			var image,
				url = $('input[name="src"]', context).val(),
				title = $('input[name="imgtitle"]', context).val(),
				description = $('input[name="description"]', context).val(),
				width = $('input[name="width"]', context).val(),
				height = $('input[name="height"]', context).val(),
				styleFloat = $('select[name="float"]', context).val(),
				percentOfSize = $('input[name="percentOfSize"]', context).val(),
				styles = [],
				style = "",
				found,
				baseUrl;
			
			if (Wysiwyg.options.controlImage && Wysiwyg.options.controlImage.forceRelativeUrls) {
				baseUrl = window.location.protocol + "//" + window.location.hostname
					+ (window.location.port ? ":" + window.location.port : "");
				if (0 === url.indexOf(baseUrl)) {
					url = url.substr(baseUrl.length);
				}
			}
			
			if (img.self) {
				// to preserve all img attributes
				$(img.self).attr("src", url)
					.attr("title", title)
					.attr("alt", description)
					.css("float", styleFloat);
				var x = percentOfSize + "%";

				if (percentOfSize.toString().match(/^[0-9]+$/)) {
					$(img.self).attr("style", "width: " + x + "; height: " + x + ";");
				}
				else {
					$(img.self).attr("width", "");
					$(img.self).attr("height", "");
					
				}
				/*
				if (width.toString().match(/^[0-9]+$/)) {
					$(img.self).attr("width", width + "%");
				} else {
					$(img.self).css("width", "");
				}

				if (height.toString().match(/^[0-9]+$/)) {
					$(img.self).attr("height", height + "%");
				} else {
					$(img.self).css("height", "");
				}
				*/

				Wysiwyg.saveContent();
			} else {
				/*
				found = width.toString().match(/^[0-9]+(px|%)?$/);
				if (found) {
					if (found[1]) {
						styles.push("width: " + width + ";");
					} else {
						styles.push("width: " + width + "px;");
					}
				}

				found = height.toString().match(/^[0-9]+(px|%)?$/);
				if (found) {
					if (found[1]) {
						styles.push("height: " + height + ";");
					} else {
						styles.push("height: " + height + "px;");
					}
				}
				*/
				
				var x = percentOfSize + "%";
				
				/*
				if (styleFloat.length > 0) {
					styles.push("float: " + styleFloat + ";");
				}*/

				if (styles.length > 0) {
					style = ' style="' + styles.join(" ") + '"';
				}
				
				image = "<img src='" + url + "' title='" + title + "' alt='" + description + "' style='width: " + x + "; height: " + x + "' />";
				alert('new image!');
				Wysiwyg.insertHtml(image);
			}
		},

		makeForm: function (form, img) {
			form.find("input[name=src]").val(img.src);
			form.find("input[name=imgtitle]").val(img.title);
			form.find("input[name=description]").val(img.alt);
			form.find('input[name="width"]').val(img.width);
			form.find('input[name="height"]').val(img.height);
			form.find('input[name="percentOfSize"]').val(img.percentOfSize);
			form.find('select[name="float"]').val(img.styleFloat);
			form.find('img').attr("src", img.src);

			form.find('img').bind("load", function () {
				if (form.find('img').get(0).naturalWidth) {
					form.find('input[name="naturalWidth"]').val(form.find('img').get(0).naturalWidth);
					form.find('input[name="naturalHeight"]').val(form.find('img').get(0).naturalHeight);
				} else if (form.find('img').attr("naturalWidth")) {
					form.find('input[name="naturalWidth"]').val(form.find('img').attr("naturalWidth"));
					form.find('input[name="naturalHeight"]').val(form.find('img').attr("naturalHeight"));
				}
			});

			form.find("input[name=src]").bind("change", function () {
				form.find('img').attr("src", this.value);
			});

			return form;
		}
	};

	$.wysiwyg.insertImage = function (object, url, attributes) {
		return object.each(function () {
			var Wysiwyg = $(this).data("wysiwyg"),
				image,
				attribute;

			if (!Wysiwyg) {
				return this;
			}

			if (!url || url.length === 0) {
				return this;
			}

			if ($.browser.msie) {
				Wysiwyg.ui.focus();
			}

			if (attributes) {
				Wysiwyg.editorDoc.execCommand("insertImage", false, "#jwysiwyg#");
				image = Wysiwyg.getElementByAttributeValue("img", "src", "#jwysiwyg#");

				if (image) {
					image.src = url;

					for (attribute in attributes) {
						if (attributes.hasOwnProperty(attribute)) {
							image.setAttribute(attribute, attributes[attribute]);
						}
					}
				}
			} else {
				Wysiwyg.editorDoc.execCommand("insertImage", false, url);
			}

			Wysiwyg.saveContent();

			$(Wysiwyg.editorDoc).trigger("editorRefresh.wysiwyg");

			return this;
		});
	};
})(jQuery);
