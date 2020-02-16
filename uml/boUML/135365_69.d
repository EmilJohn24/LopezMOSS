format 222

classcanvas 128069 class_ref 138565 // ComparisonStrategy
  classdiagramsettings member_max_width 0 end
  xyzwh 327.037 678.628 2011 166 81
end
classcanvas 128197 class_ref 138949 // Token
  classdiagramsettings member_max_width 0 end
  xyzwh 480.217 412.614 2000 104 184
end
classcanvas 128325 class_ref 139717 // TokenHashingStrategy
  classdiagramsettings member_max_width 0 end
  xyzwh 85.337 414.981 2005 184 84
end
classcanvas 128453 class_ref 139589 // TokenClusterOccurrenceTable
  classdiagramsettings member_max_width 0 end
  xyzwh 140.837 210.768 2016 234 149
end
classcanvas 128581 class_ref 138693 // HashingTokenizer
  classdiagramsettings member_max_width 0 end
  xyzwh 120.292 580.244 2000 187 184
end
classcanvas 128709 class_ref 139077 // TokenCluster
  classdiagramsettings member_max_width 0 end
  xyzwh 474.138 234.986 2006 122 101
end
packagecanvas 129221 
  package_ref 135237 // algorithm
    xyzwh 72.08 152.94 2012 540 721
end
classcanvas 129989 class_ref 140869 // Projects
  classdiagramsettings member_max_width 0 end
  xyz 1113 329.68 2000
end
classcanvas 130117 class_ref 140613 // ProjectBuilder
  classdiagramsettings member_max_width 0 end
  xyz 1392.88 111.26 3011
end
classcanvas 130245 class_ref 139973 // MultiProjectStorage
  classdiagramsettings member_max_width 0 end
  xyz 819.12 135.06 2000
end
classcanvas 130373 class_ref 140101 // Project
  classdiagramsettings member_max_width 0 end
  xyz 1114.8 129.06 2006
end
packagecanvas 130757 
  package_ref 135365 // project
    xyzwh 744.44 31.68 2000 925 503
end
packagecanvas 132037 
  package_ref 135493 // projectpairmachine
    xyzwh 735.78 627.02 2000 893 416
end
classcanvas 132165 class_ref 141253 // MultiProjectComparison
  classdiagramsettings member_max_width 0 end
  xyz 1198.36 722.28 2005
end
classcanvas 132293 class_ref 141381 // ProjectsCorrelationMatrix
  classdiagramsettings member_max_width 0 end
  xyz 831.14 878.06 2005
end
classcanvas 132421 class_ref 142021 // SimpleMultiProjectComparison
  classdiagramsettings member_max_width 0 end
  xyz 814.78 705.54 2017
end
classcanvas 135877 class_ref 149061 // PathFilter
  classdiagramsettings member_max_width 0 end
  xyz 1401.92 340.4 2021
end
classcanvas 136261 class_ref 149701 // ProjectFlatReaderDistributor
  classdiagramsettings member_max_width 0 end
  xyz 819 315 2022
end
classcanvas 136389 class_ref 149573 // PathFilterBuilder
  classdiagramsettings member_max_width 0 end
  xyz 1521.08 307.12 2010
end
classcanvas 136773 class_ref 141637 // ResultSet
  classdiagramsettings member_max_width 0 end
  xyz 1489.28 834.68 2005
end
classcanvas 136901 class_ref 141893 // ProjectsCorrelationMatrixBuilder
  classdiagramsettings member_max_width 0 end
  xyz 1080.08 847 2005
end
classcanvas 137285 class_ref 141509 // ResultRow
  classdiagramsettings member_max_width 0 end
  xyz 1355.28 843.92 2005
end
classcanvas 137669 class_ref 141765 // ResultRecord
  classdiagramsettings member_max_width 0 end
  xyz 1500.68 687.8 2010
end
relationcanvas 128837 relation_ref 137669 // <unidirectional association>
  decenter_end 483
  from ref 128453 z 2013 stereotype "<<HashMap>>" xyz 381 282 2013 to ref 128709
  role_a_pos 382 256 3000 no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 128965 relation_ref 137413 // <unidirectional association>
  from ref 128709 z 2007 stereotype "<<Collection>>" xyz 494 337 2007 to ref 128197
  role_a_pos 545 387 3000 no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 129093 relation_ref 137797 // <realization>
  from ref 128325 z 2012 to ref 128069
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 130501 relation_ref 138053 // <unidirectional association>
  from ref 130245 z 2007 stereotype "<<Collection>>" xyz 991 194 2007 to ref 130373
  role_a_pos 1043 170 3000 no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 132549 relation_ref 139589 // <unidirectional association>
  from ref 132421 z 2018 to ref 128069
  role_a_pos 505 695 3000 no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 133061 relation_ref 139845 // <dependency>
  from ref 132421 z 2018 to ref 132293
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 133189 relation_ref 139973 // <generalisation>
  from ref 132421 z 2018 to ref 132165
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 133445 relation_ref 140101 // <dependency>
  from ref 130117 z 3012 to ref 130373
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 133573 relation_ref 140229 // <unidirectional association>
  from ref 129989 z 2007 to ref 130373
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
simplerelationcanvas 134853 simplerelation_ref 134597
  from ref 129989 z 2017 to ref 129221
end
simplerelationcanvas 135749 simplerelation_ref 134725
  from ref 132037 z 2001 to ref 129989
end
relationcanvas 136517 relation_ref 148421 // <dependency>
  from ref 136389 z 2022 to ref 135877
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 136645 relation_ref 148549 // <composition>
  from ref 135877 z 3012 to ref 130117
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 137029 relation_ref 148677 // <dependency>
  from ref 136901 z 2006 to ref 132293
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 137413 relation_ref 148933 // <aggregation>
  from ref 137285 z 2006 to ref 136901
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 137541 relation_ref 149061 // <aggregation>
  from ref 136773 z 2006 to ref 137285
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
relationcanvas 137797 relation_ref 149189 // <aggregation>
  from ref 137669 z 2011 to ref 136773
  no_role_a no_role_b
  no_multiplicity_a no_multiplicity_b
end
preferred_whz 1331 770 0.7
end
