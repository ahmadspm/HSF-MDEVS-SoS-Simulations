# HSF-MDEVS-SoS-Simulations

This repository contains Abstract models for expriementing HSF with MARKOV Discrevent Event Sysems Specifications (MDEVS) which is a Markovian description of DEVS. Markov models are described using HSF DSL developed using Eclipse Modelling Framework coupled with Xtext and Xtend technologies. For Stochastic simulation of smart system architecutre models MS4 me TOOL Suite is used which provides Markvian classes for complex systems simulations.  The Simulations are done using formal transformation methods aided with Model Driven Engineeirng. 

HSF code is transformed from CSs and System of Ssytems (SoS) coalitionS for DEVS and SES from HSF.  

There are certain limitations in terms of Transformation as MDEVS and HSF have certian differences considering SoS architectural elements. For this, formally founded bisimulation transofrmation rules assist for one to one mapping for each of the Architectural Elment from HSF to DEVS specificaitons. 

Formal transformation of HSF based SoS archiecutre models are validated through Xtend transfomred to DEVSNL model and SES modles for DEVS. Certain Tweaking and adjustements have been made manually at later stage to conform the mapping to corssponding Markov Classes, respective to atomic modles. Additionally Experimental Frames are added to SES as coupled model for representing SoS coalitiions. 

Synthatic Datasets are generated for SoS Coalition1 and Coalition2 for performing various Software Architecural Analysis such as:
Mission completion Analysis,
SoS Behaviour Analysis,
and SoS coalitions conformance to system requirements during missions

This is unique apparoch exploring SoS Dynamics using Markovian Semantics specifically adopted for Software Architecure Models. 
