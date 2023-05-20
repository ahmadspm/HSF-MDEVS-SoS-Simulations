# Smart Systems Software Architecture Modelling and Simualtion- HSF-MDEVS-SoS-Simulations

The SoS are modern Smart Systems composed of legacy software systems, Internet of Things (IoTs) and Cyber-Physical Systems (CPS). Validation of such systems architecture models is a big challenge and complex problem as such systems cannot be tested physically while existing validation methods are not sufficient to support the models validation to full. This research work is a first of its kind effort combining Formal Markovian Semantics based simulations of software-intensive systems using Discrete Events Systems Specification. To an extent we have been able to using formal methods and model-driven engineering to transform SoS architecture models to Markovian DEVS equivalents
This repository contains Abstract models for experimenting HSF with MARKOV Discrete Event Systems Specifications (MDEVS) which is a Markovian description of DEVS. Markov models are described using HSF DSL developed using Eclipse Modelling Framework coupled with Xtext and Xtend technologies. For Stochastic simulation of smart system architecture models MS4 me TOOL Suite is used which provides Markvian classes for complex systems simulations.  The Simulations are done using formal transformation methods aided with Model Driven Engineering. HSF code is transformed from CSs and System of Systems (SoS) Coalitions for DEVS and SES from HSF.  
There are certain limitations in terms of Transformation as MDEVS and HSF have certain differences considering SoS architectural elements. For this, formally founded bisimulation transformation rules assist for one to one mapping for each of the Architectural Element from HSF to DEVS specifications. 

Formal transformation of HSF based SoS architecture models are validated through Xtend transformed to DEVSNL model and SES models for DEVS. Certain Tweaking and adjustments have been made manually at later stage to conform the mapping to corresponding Markov Classes, respective to atomic models. Additionally Experimental Frames are added to SES as coupled model for representing SoS coalitions. 

Synthetic Datasets are generated for SoS Coalition1 and Coalition2 for performing various Software Architectural Analysis such as:
•	Mission completion Analysis,
•	SoS Behaviour Analysis,
•	and SoS coalitions conformance to system requirements during missions

This is unique approach exploring SoS Dynamics using Markovian Semantics specifically adopted for Software Architecture Models.
