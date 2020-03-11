<a href="https://www.usc.edu/"><img src="https://identity.usc.edu/files/2019/01/PrimShield-Word_SmallUse_CardOnTrans.png"/><a>


# Primary School Building
Source code of Primary School Building simulation example

<!--
## How to cite 

```
%@INPROCEEDINGS{LymperopoulosACC2020, 
author={G. Lymperopoulos and P. M. Papadopoulos and Petros Ioannou and M. M. Polycarpou}, 
booktitle={American Control Conference (ACC)}, 
title={Distributed Adaptive Control of Air Handling Units for Interconnected
Building Zones}, 
year={2020}, 
pages={}, 
doi={}, 
ISSN={}, 
month={}}
```
-->
## Requirements 

* [EnergyPlus](https://energyplus.net/)
* [Matlab](http://www.mathworks.com/)

## How to cite 

```

@Article{Lymperopoulos_2020,
  author    = {Georgios Lymperopoulos and Petros Ioannou},
  title     = {Building Temperature Regulation in a Multi-Zone {HVAC} System using Distributed Adaptive Control},
  journal   = {Energy and Buildings},
  year      = {2020},
  pages     = {109825},
  month     = {jan},
  doi       = {10.1016/j.enbuild.2020.109825},
  publisher = {Elsevier {BV}},
}

```
## Abstract
During recent years there have been considerable research efforts on improving energy efficiency of buildings. Since Heating, Ventilation and Air-Conditioning (HVAC) systems are responsible for a big part of energy consumption, developing efficient HVAC control systems is crucial. In most of the developed approaches, precise knowledge of system parameters and/or adequate historical data is required. However, these approaches may not perform as well in the presence of dynamic parameter changes due to human activity, material degradation, and wear and tear, or disturbances and other operational uncertainties due to occupancy, solar gains, electrical equipment, and weather conditions. In this paper, we consider buildings with several climate zones and propose a distributed adaptive control scheme for a multi-zone HVAC system which can effectively regulate zone temperature by applying on-line learning and assuming exchange of information between neighboring zones. The controller of each zone achieves the local objective of controlling zone temperature by compensating for the effects of neighboring zones as well as for possible changes in the parameters of the system. Despite the exchange of information, each local controller does not know how the control actions and temperature of a neighboring zone affect the temperature of its own zone. For this reason, each local controller is estimating the parameters of the interconnections in real time and uses them together with the exchanged information to provide a more accurate local zone temperature control. The proposed method is illustrated using an example of temperature control in a six-zone building as well as a large school building, which are implemented in a Building Controls Virtual Test Bed (BCVTB) environment using EnergyPlus and MATLAB/Simulink.

Keywords: Temperature regulation; HVAC systems; Smart buildings; Distributed control; Adaptive control


