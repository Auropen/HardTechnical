@echo off

set modid=techplex
set block=copperOre
set resourcePath=src/main/resources/assets/%modid%/

(
  echo {
  echo "variants": {
  echo "normal": { "model": "%modid%:%block%" }
  echo }
  echo }
) > %resourcePath%/blockstates/%block%.json

(
  echo {
  echo "parent": "block/cube_all",
  echo "textures": {
  echo "all": "%modid%:blocks/%block%"
  echo }
  echo }
) > %resourcePath%/models/block/%block%.json

(
  echo {
  echo "parent": "%modid%:block/%block%",
  echo "display": {
  echo "thirdperson": {
  echo "rotation": [ -90, 0, 0 ],
  echo "translation": [ 0, 1, -3 ],
  echo "scale": [ 0.55, 0.55, 0.55 ]
  echo },
  echo "firstperson": {
  echo "rotation": [ 0, -135, 25 ],
  echo "translation": [ 0, 4, 2 ],
  echo "scale": [ 1.7, 1.7, 1.7 ]
  echo }
  echo }
  echo }
) > %resourcePath%/models/item/%block%.json
