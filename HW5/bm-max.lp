\ Bipartite Matching problem from page 62 of slides, MAX form
Problem
  BM
Maximize
  value: e16 + e17 + e19 + e26 + e27 + e36 + e38 + e39 + e47 + e410 + e57 + e510
Subject To
  v1: e16 + e17 + e19 <= 1
  v2: e26 + e27 <= 1
  v3: e36 + e38 + e39 <= 1
  v4: e47 + e410 <= 1
  v5: e57 + e510 <= 1
  v6: e16 + e26 + e36 <= 1
  v7: e17 + e27 + e47 + e57 <= 1
  v8: e38 <= 1
  v9: e19 + e39 <= 1
 v10: e410 + e510 <= 1
\ all variables are implicitly non-negative
End
