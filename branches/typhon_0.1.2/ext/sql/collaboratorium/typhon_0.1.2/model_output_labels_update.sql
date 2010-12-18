-- phpMyAdmin SQL Dump
-- version 3.3.2deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Czas wygenerowania: 05 Gru 2010, 22:15
-- Wersja serwera: 5.1.41
-- Wersja PHP: 5.3.2-1ubuntu4.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Baza danych: `lportal`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla  `ModelOutputItem`
--

CREATE TABLE IF NOT EXISTS `ModelOutputItem` (
  `modelOutputItemModifierPK` bigint(20) NOT NULL,
  `modelId` bigint(20) DEFAULT NULL,
  `modelOutputItemId` bigint(20) DEFAULT NULL,
  `modelOutputItemOrder` int(11) DEFAULT NULL,
  `itemType` varchar(75) DEFAULT NULL,
  `modelItemRangePolicy` varchar(75) DEFAULT NULL,
  `modelItemRangeMessage` longtext,
  `modelItemErrorPolicy` varchar(75) DEFAULT NULL,
  `modelItemErrorMessage` longtext,
  `modelItemIsVisible` tinyint(4) DEFAULT NULL,
  `relatedOutputItem` bigint(20) DEFAULT NULL,
  `modelItemLabelFormat` varchar(4096) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`modelOutputItemModifierPK`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `ModelOutputItem`
--

INSERT INTO `ModelOutputItem` (`modelOutputItemModifierPK`, `modelId`, `modelOutputItemId`, `modelOutputItemOrder`, `itemType`, `modelItemRangePolicy`, `modelItemRangeMessage`, `modelItemErrorPolicy`, `modelItemErrorMessage`, `modelItemIsVisible`, `relatedOutputItem`, `modelItemLabelFormat`) VALUES
(1, 740, 3033, 2, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">Many of the fastest developing and larger nations: China, India, South Africa, Mexico, Brazil, Indonesia, and other large developing Asian countries (from <a href="/web/guest/resources/-/wiki/Main/C-LEARN">C-LEARN</a>).</div>'),
(2, 740, 3036, 3, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">Smaller developing nations in the Middle East, Latin America, Africa, and Asia (from <a href="/web/guest/resources/-/wiki/Main/C-LEARN">C-LEARN</a>).</div>'),
(3, 740, 3035, 7, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">Global average temperature change in degrees Celsius (C&deg;) from pre-industrial values(from <a href="/web/guest/resources/-/wiki/Main/C-LEARN">C-LEARN</a>)</div>'),
(4, 740, 2892, 31, 'NORMAL', '', '', '', '', 1, NULL, ''),
(5, 740, 2891, 42, 'NORMAL', '', '', '', '', 1, NULL, ''),
(6, 740, 3025, 1, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">Many of the most developed nations: US, EU (27 countries), Norway and Sweden, Russia and the former Soviet States, Japan, Canada, South Korea, New Zealand, and Australia (from <a href="/web/guest/resources/-/wiki/Main/C-LEARN">C-LEARN</a>).</div>'),
(7, 740, 2890, 34, 'NORMAL', '', '', '', '', 1, NULL, ''),
(8, 740, 3021, 5, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">Atmospheric CO2 concentration in parts per million - ppm (from <a href="/web/guest/resources/-/wiki/Main/C-LEARN">C-LEARN</a>).</div>'),
(9, 740, 3013, 25, 'NORMAL', '', '', '', '', 1, NULL, ''),
(10, 740, 2895, 29, 'NORMAL', '', '', '', '', 1, NULL, ''),
(11, 740, 3014, 37, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(12, 740, 3015, 28, 'NORMAL', '', '', '', '', 1, NULL, ''),
(13, 740, 2893, 26, 'NORMAL', '', '', '', '', 1, NULL, ''),
(14, 740, 2894, 38, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(15, 740, 3010, 35, 'NORMAL', '', '', '', '', 1, NULL, ''),
(16, 740, 3011, 43, 'NORMAL', '', '', '', '', 1, NULL, ''),
(17, 740, 3012, 32, 'NORMAL', '', '', '', '', 1, NULL, ''),
(18, 740, 3045, 11, 'NORMAL', '', '', '', '', 1, NULL, NULL),
(19, 740, 3046, 9, 'NORMAL', '', '', '', '', 1, NULL, NULL),
(20, 740, 3040, 14, 'NORMAL', '', '', '', '', 1, NULL, NULL),
(21, 740, 3043, 15, 'CONF_INTERVAL_95', '', '', '', '', 1, 3040, NULL),
(22, 740, 3044, 12, 'NORMAL', '', '', '', '', 1, NULL, NULL),
(23, 740, 3041, 16, 'NORMAL', '', '', '', '', 1, NULL, NULL),
(24, 740, 3042, 17, 'CONF_INTERVAL_5', '', '', '', '', 1, 3040, NULL),
(101, 240, 242, NULL, '', '', '', '', '', 1, NULL, NULL),
(102, 240, 246, NULL, '', '', '', '', '', 1, NULL, NULL),
(103, 240, 245, NULL, '', '', '', '', '', 1, NULL, NULL),
(104, 240, 241, NULL, '', '', '', '', '', 1, NULL, NULL),
(105, 240, 263, NULL, '', '', '', '', '', 1, NULL, NULL),
(106, 623, 2957, NULL, '', '', '', '', '', 1, NULL, NULL),
(107, 623, 2956, NULL, '', '', '', '', '', 1, NULL, NULL),
(108, 623, 2955, NULL, '', '', '', '', '', 1, NULL, NULL),
(109, 623, 2954, NULL, '', '', '', '', '', 1, NULL, NULL),
(110, 623, 2958, NULL, '', '', '', '', '', 1, NULL, NULL),
(111, 623, 2953, NULL, '', '', '', '', '', 1, NULL, NULL),
(112, 623, 2952, NULL, '', '', '', '', '', 1, NULL, NULL),
(113, 623, 2951, NULL, '', '', '', '', '', 1, NULL, NULL),
(201, 760, 3103, 15, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <i>90% confidence intervals shown</i> <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(202, 760, 3104, 16, 'CONF_INTERVAL_5', '', '', '', '', 1, 3103, ''),
(203, 760, 3102, 17, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(204, 760, 3109, 9, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(205, 760, 3107, 11, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%unit) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(206, 760, 3108, 12, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%unit) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(207, 760, 3105, 18, 'CONF_INTERVAL_95', '', '', '', '', 1, 3103, ''),
(208, 760, 3106, 13, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%unit) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(301, 783, 3476, 11, 'NORMAL', '', '', '', '', 1, NULL, ''),
(302, 783, 3475, 13, 'NORMAL', 'DISPLAY_AVAILBLE_NO_MSG', '', 'DISPLAY_AVAILBLE_NO_MSG', '', 1, NULL, ''),
(303, 783, 3474, 14, 'NORMAL', 'DISPLAY_AVAILBLE_NO_MSG', '', 'DISPLAY_AVAILBLE_NO_MSG', '', 1, NULL, ''),
(304, 783, 3440, 40, 'NORMAL', '', '', '', '', 0, NULL, ''),
(305, 783, 3470, 17, 'NORMAL', '', '', '', '', 1, NULL, ''),
(306, 783, 3471, 18, 'NORMAL', '', '', '', '', 1, NULL, ''),
(307, 783, 3472, 19, 'NORMAL', '', '', '', '', 1, NULL, ''),
(308, 783, 3473, 15, 'NORMAL', 'DISPLAY_AVAILBLE_NO_MSG', '', 'DISPLAY_AVAILBLE_NO_MSG', '', 1, NULL, ''),
(309, 783, 3469, 20, 'NORMAL', '', '', '', '', 1, NULL, ''),
(401, 802, 3534, NULL, '', '', '', '', '', 1, NULL, ''),
(402, 802, 3533, NULL, '', '', '', '', '', 1, NULL, ''),
(403, 802, 3532, NULL, '', '', '', '', '', 1, NULL, ''),
(404, 802, 3537, NULL, '', '', '', '', '', 1, NULL, ''),
(405, 802, 3538, NULL, '', '', '', '', '', 1, NULL, ''),
(406, 802, 3535, NULL, '', '', '', '', '', 1, NULL, ''),
(407, 802, 3536, NULL, '', '', '', '', '', 1, NULL, ''),
(408, 802, 3539, NULL, '', '', '', '', '', 1, NULL, ''),
(501, 821, 3568, 14, 'NORMAL', '', '', '', '', 1, NULL, ''),
(502, 821, 3569, 12, 'NORMAL', '', '', '', '', 1, NULL, ''),
(503, 821, 3566, 15, 'NORMAL', '', '', '', '', 1, NULL, ''),
(504, 821, 3567, 16, 'NORMAL', '', '', '', '', 1, NULL, ''),
(505, 821, 3564, 18, 'NORMAL', '', '', '', '', 1, NULL, ''),
(506, 821, 3565, 19, 'NORMAL', '', '', '', '', 1, NULL, ''),
(507, 821, 3563, 20, 'NORMAL', '', '', '', '', 1, NULL, ''),
(508, 821, 3562, 21, 'NORMAL', '', '', '', '', 1, NULL, ''),
(601, 841, 3616, 13, 'NORMAL', '', '', '', '', 1, NULL, ''),
(602, 841, 3617, 11, 'NORMAL', '', '', '', '', 1, NULL, ''),
(603, 841, 3610, 17, 'NORMAL', '', '', '', '', 1, NULL, ''),
(604, 841, 3611, 18, 'NORMAL', '', '', '', '', 1, NULL, ''),
(605, 841, 3614, 14, 'NORMAL', '', '', '', '', 1, NULL, ''),
(606, 841, 3615, 15, 'NORMAL', '', '', '', '', 1, NULL, ''),
(607, 841, 3612, 19, 'NORMAL', '', '', '', '', 1, NULL, ''),
(608, 841, 3613, 20, 'NORMAL', '', '', '', '', 1, NULL, ''),
(701, 862, 3620, 9, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">Sea level change in millimeters (mm) from 2000 (from C-LEARN)</div>'),
(702, 862, 3645, 13, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">      Cost of efforts to prevent climate change (e.g., by reducing emissions). Costs are shown as a % of World GDP (Gross Domestic Product). Values for 2010 contest are from <a href="/web/guest/resources/-/wiki/Main/EMF%2022%20response%20surfaces">EMF 22 response surfaces</a>. Values for 2009 contest are from <a href="/web/guest/resources/-/wiki/Main/IGSM%20response%20surface">IGSM response surface</a>, <a href="/web/guest/resources/-/wiki/Main/MERGE%20response%20surface">MERGE response surface</a>, and <a href="/web/guest/resources/-/wiki/Main/MiniCAM%20response%20surface">MiniCAM response surface</a>.  </div>'),
(703, 862, 3646, 11, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">      Cost of efforts to prevent climate change (e.g., by reducing emissions). Costs are shown as a % of World GDP (Gross Domestic Product). Values for 2010 contest are from <a href="/web/guest/resources/-/wiki/Main/EMF%2022%20response%20surfaces">EMF 22 response surfaces</a>. Values for 2009 contest are from <a href="/web/guest/resources/-/wiki/Main/IGSM%20response%20surface">IGSM response surface</a>, <a href="/web/guest/resources/-/wiki/Main/MERGE%20response%20surface">MERGE response surface</a>, and <a href="/web/guest/resources/-/wiki/Main/MiniCAM%20response%20surface">MiniCAM response surface</a>.  </div>'),
(704, 862, 3643, 18, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">      Cost of efforts to prevent climate change (e.g., by reducing emissions). Costs are shown as a % of World GDP (Gross Domestic Product). Values for 2010 contest are from <a href="/web/guest/resources/-/wiki/Main/EMF%2022%20response%20surfaces">EMF 22 response surfaces</a>. Values for 2009 contest are from <a href="/web/guest/resources/-/wiki/Main/IGSM%20response%20surface">IGSM response surface</a>, <a href="/web/guest/resources/-/wiki/Main/MERGE%20response%20surface">MERGE response surface</a>, and <a href="/web/guest/resources/-/wiki/Main/MiniCAM%20response%20surface">MiniCAM response surface</a>.  </div>'),
(705, 862, 3644, 16, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">      Cost of efforts to prevent climate change (e.g., by reducing emissions). Costs are shown as a % of World GDP (Gross Domestic Product). Values for 2010 contest are from <a href="/web/guest/resources/-/wiki/Main/EMF%2022%20response%20surfaces">EMF 22 response surfaces</a>. Values for 2009 contest are from <a href="/web/guest/resources/-/wiki/Main/IGSM%20response%20surface">IGSM response surface</a>, <a href="/web/guest/resources/-/wiki/Main/MERGE%20response%20surface">MERGE response surface</a>, and <a href="/web/guest/resources/-/wiki/Main/MiniCAM%20response%20surface">MiniCAM response surface</a>.  </div>'),
(706, 862, 3649, 17, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">      Cost of efforts to prevent climate change (e.g., by reducing emissions). Costs are shown as a % of World GDP (Gross Domestic Product). Values for 2010 contest are from <a href="/web/guest/resources/-/wiki/Main/EMF%2022%20response%20surfaces">EMF 22 response surfaces</a>. Values for 2009 contest are from <a href="/web/guest/resources/-/wiki/Main/IGSM%20response%20surface">IGSM response surface</a>, <a href="/web/guest/resources/-/wiki/Main/MERGE%20response%20surface">MERGE response surface</a>, and <a href="/web/guest/resources/-/wiki/Main/MiniCAM%20response%20surface">MiniCAM response surface</a>.  </div>'),
(707, 862, 3647, 12, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">      Cost of efforts to prevent climate change (e.g., by reducing emissions). Costs are shown as a % of World GDP (Gross Domestic Product). Values for 2010 contest are from <a href="/web/guest/resources/-/wiki/Main/EMF%2022%20response%20surfaces">EMF 22 response surfaces</a>. Values for 2009 contest are from <a href="/web/guest/resources/-/wiki/Main/IGSM%20response%20surface">IGSM response surface</a>, <a href="/web/guest/resources/-/wiki/Main/MERGE%20response%20surface">MERGE response surface</a>, and <a href="/web/guest/resources/-/wiki/Main/MiniCAM%20response%20surface">MiniCAM response surface</a>.  </div>'),
(708, 862, 3648, 14, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">      Cost of efforts to prevent climate change (e.g., by reducing emissions). Costs are shown as a % of World GDP (Gross Domestic Product). Values for 2010 contest are from <a href="/web/guest/resources/-/wiki/Main/EMF%2022%20response%20surfaces">EMF 22 response surfaces</a>. Values for 2009 contest are from <a href="/web/guest/resources/-/wiki/Main/IGSM%20response%20surface">IGSM response surface</a>, <a href="/web/guest/resources/-/wiki/Main/MERGE%20response%20surface">MERGE response surface</a>, and <a href="/web/guest/resources/-/wiki/Main/MiniCAM%20response%20surface">MiniCAM response surface</a>.  </div>'),
(709, 862, 3652, 21, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden"> Cost of damages caused by climate change (e.g., damages from rising sea level, hurricanes, droughts, etc.). Costs are shown as a % of World GDP (Gross Domestic Product). Values for <a href="/web/guest/resources/-/wiki/Main/PAGE">PAGE</a> model shown with 90% confidence intervals. That is, according to the model, there is only a 10% chance that the costs would be outside the shaded range. </div>'),
(710, 862, 3654, 23, 'CONF_INTERVAL_95', '', '', '', '', 1, 3652, ''),
(711, 862, 3653, 22, 'CONF_INTERVAL_5', '', '', '', '', 1, 3652, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden"> Cost of damages caused by climate change (e.g., damages from rising sea level, hurricanes, droughts, etc.). Costs are shown as a % of World GDP (Gross Domestic Product). Values for <a href="/web/guest/resources/-/wiki/Main/PAGE">PAGE</a> model shown with 90% confidence intervals. That is, according to the model, there is only a 10% chance that the costs would be outside the shaded range. </div>'),
(712, 862, 3655, 20, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden"> Cost of damages caused by climate change (e.g., damages from rising sea level, hurricanes, droughts, etc.). Costs are shown as a % of World GDP (Gross Domestic Product). Values for <a href="/web/guest/resources/-/wiki/Main/DICE">DICE</a> model. </div>'),
(713, 862, 3656, 15, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 0, NULL, '%label (%unit) <img class=''output-info-trigger'' src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /><div class="popup-info-box hidden">      Cost of efforts to prevent climate change (e.g., by reducing emissions). Costs are shown as a % of World GDP (Gross Domestic Product). Values for 2010 contest are from <a href="/web/guest/resources/-/wiki/Main/EMF%2022%20response%20surfaces">EMF 22 response surfaces</a>. Values for 2009 contest are from <a href="/web/guest/resources/-/wiki/Main/IGSM%20response%20surface">IGSM response surface</a>, <a href="/web/guest/resources/-/wiki/Main/MERGE%20response%20surface">MERGE response surface</a>, and <a href="/web/guest/resources/-/wiki/Main/MiniCAM%20response%20surface">MiniCAM response surface</a>.  </div>'),
(714, 863, 3620, 9, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(715, 863, 3645, 13, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(716, 863, 3646, 11, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(717, 863, 3643, 18, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(718, 863, 3644, 16, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(719, 863, 3649, 17, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(720, 863, 3647, 12, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(721, 863, 3648, 14, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(722, 863, 3652, 21, 'NORMAL', '', '', '', '', 1, NULL, 'PAGE (%GDP vs. baseline) <i>90% confidence interval shown</i><a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(723, 863, 3654, 23, 'CONF_INTERVAL_95', '', '', '', '', 1, 3652, ''),
(724, 863, 3653, 22, 'CONF_INTERVAL_5', '', '', '', '', 1, 3652, 'PAGE %label 5% confidence interval <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(725, 863, 3655, 20, 'NORMAL', '', '', '', '', 1, NULL, 'DICE (%GDP vs. baseline)  <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(726, 863, 3656, 15, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 0, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(727, 864, 3620, 9, 'NORMAL', '', '', '', '', 1, NULL, '%label (%unit) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(728, 864, 3645, 13, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(729, 864, 3646, 11, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(730, 864, 3643, 18, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(731, 864, 3644, 16, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(732, 864, 3649, 17, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(733, 864, 3647, 12, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(734, 864, 3648, 14, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 1, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(735, 864, 3652, 21, 'NORMAL', '', '', '', '', 1, NULL, 'PAGE (%GDP vs. baseline) <i>90% confidence interval shown</i><a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(736, 864, 3654, 23, 'CONF_INTERVAL_95', '', '', '', '', 1, 3652, ''),
(737, 864, 3653, 22, 'CONF_INTERVAL_5', '', '', '', '', 1, 3652, 'PAGE %label 5% confidence interval <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(738, 864, 3655, 20, 'NORMAL', '', '', '', '', 1, NULL, 'DICE (%GDP vs. baseline)  <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>'),
(739, 864, 3656, 15, 'NORMAL', '', '', 'NO_DISPLAY_WITH_MSG', '', 0, NULL, '%label (%GDP vs. baseline) <a href=''/web/guest/resources/-/wiki/Main/Plan+index+definitions''><img src=''/collaboratorium-theme/images/qustion_icon.png'' alt=''question'' /></a>');

