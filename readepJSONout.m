clear all; %clc;
clear dat
% 
currentFolder = pwd;
addpath([currentFolder,'\jsonlab'])
idcs   = strfind(currentFolder,'\');
newdir = currentFolder(1:idcs(end)-1);
dat=loadjson([newdir,'\ePlus\ASHRAE9012016_SchoolPrimary_Denver.epJSON']);


% addpath('C:\Users\jhoke\University of Cyprus\Panayiotis Papadopoullos - Georgios Lymperopoulos\Simulations\primary_school\Full_Simulation\Complete building simulator\Simulink\jsonlab')
% dat=loadjson('C:\Users\jhoke\University of Cyprus\Panayiotis Papadopoullos - Georgios Lymperopoulos\Simulations\primary_school\Full_Simulation\Complete building simulator\ePlus\ASHRAE9012016_SchoolPrimary_Denver.epJSONout');
% 

%%
%clc;
% simulation step
Ts=3600/dat.Timestep.Timestep_0x20_1.number_of_timesteps_per_hour;

%%% Global parameters %%%
Cv = 718; % Specific heat of air at constant volume (J/kg.K)
Cpa = 1005; % Specific heat of air at constant pressure (J/kg.K)
pa = 1.18; % air density (kg/m3)
pw = 1000; % water density (kg/m3)
Cpw = 4180; % Specific heat of water at constant pressure (J/kg.K)

clear building 
%%%%%%%%%%%%%%%%%%%%%%%%%% Zone Parameters %%%%%%%%%%%%%%%%%%%%%%%%%%%%%

building.zonenames=fieldnames(dat.Zone);
for zone=1:numel(fieldnames(dat.Zone))
    building.vol(zone,:)=eval(['dat.Zone.' building.zonenames{zone} '.volume']);
    building.zone(zone).name=building.zonenames{zone};
    building.zone(zone).vol=building.vol(zone,:);
    building.zone(zone).tag=strrep(building.zonenames{zone},'_ZN_1_FLR_1','');
    building.zone(zone).tag=strrep(building.zone(zone).tag,'_','');
end

% read material group
materialnames = fieldnames(dat.Material);

for i=1:numel(materialnames)
   building.material(i).name=materialnames{i}; 
   building.material(i).tag=strrep(materialnames{i},'_0x20_',' ');
   building.material(i).tag=strrep(building.material(i).tag,'x0x31_','1');
   building.material(i).tag=strrep(building.material(i).tag,'x0x32_','2');
   building.material(i).tag=strrep(building.material(i).tag,'_0x2D_','-');
   building.material(i).conductivity=eval(['dat.Material.' building.material(i).name '.conductivity']);
   building.material(i).thickness=eval(['dat.Material.' building.material(i).name '.thickness']);
   building.material(i).Res=building.material(i).thickness/building.material(i).conductivity;
end

% read material no mass group
materialnomassnames = fieldnames(dat.Material_0x3A_NoMass);

k=numel(materialnames)+1:numel(materialnames)+1+numel(materialnomassnames);

for i=1:numel(materialnomassnames)
   building.material(k(i)).name=materialnomassnames{i}; 
   building.material(k(i)).tag=strrep(materialnomassnames{i},'_0x20_',' ');
   building.material(k(i)).tag=strrep(building.material(k(i)).tag,'x0x31_','1');
   building.material(k(i)).tag=strrep(building.material(k(i)).tag,'x0x32_','2');
   building.material(k(i)).tag=strrep(building.material(k(i)).tag,'_0x2D_','-');
   building.material(k(i)).Res=eval(['dat.Material_0x3A_NoMass.' building.material(k(i)).name '.thermal_resistance']);
end

% read dat.WindowMaterial_0x3A_Glazing   
windowmaterialnames = fieldnames(dat.WindowMaterial_0x3A_Glazing);
h=k(end)+1:k(end)+1+numel(windowmaterialnames);

for i=1:numel(windowmaterialnames)
   building.material(h(i)).name=windowmaterialnames{i}; 
   building.material(h(i)).tag=strrep(windowmaterialnames{i},'_0x20_',' ');
   building.material(h(i)).tag=strrep(building.material(h(i)).tag,'x0x31_','1');
   building.material(h(i)).tag=strrep(building.material(h(i)).tag,'x0x32_','2');
   building.material(h(i)).tag=strrep(building.material(h(i)).tag,'_0x2D_','-');
   building.material(h(i)).conductivity=eval(['dat.WindowMaterial_0x3A_Glazing.' building.material(h(i)).name '.conductivity']);
   building.material(h(i)).thickness=eval(['dat.WindowMaterial_0x3A_Glazing.' building.material(h(i)).name '.thickness']);
   building.material(h(i)).Res=building.material(h(i)).thickness/building.material(h(i)).conductivity;
end

% read construction group
constructionnames = fieldnames(dat.Construction);

% remove windows from construction group
p=1;
for g=1:numel(fieldnames(dat.Construction))
    temp=constructionnames{g,:};
    if temp(1:6)=='Window'
        p=p;
    else
        constructionnames_str(p)=cellstr(temp);
        p=p+1;
    end
end
constructionnames=constructionnames_str';

for i=1:numel(constructionnames)
    building.construction(i).name=constructionnames{i};
    building.construction(i).layers=eval(['dat.Construction.' building.construction(i).name]);
    layersnames{i}=building.construction(i).layers;
    building.construction(i).layersname=layersnames{i};
    fnames=fieldnames(building.construction(i).layersname);
    totRes=0;
    for j=1:numel(fieldnames(building.construction(i).layers))  
        if isa(eval(['dat.Construction.' building.construction(i).name '.' fnames{j}]),'char')
           % building.material.tag='100mm Normalweight concrete floor'      
           m =getfield(eval(['dat.Construction.' building.construction(i).name ]),fnames{j});
           ind=find(strcmp({building.material.tag}, m)==1);
           totRes=totRes+building.material(ind).Res;      
        end
    end
    building.construction(i).Uvalue=1/totRes;
end

BuildingSurfaces=fieldnames(dat.BuildingSurface_0x3A_Detailed);
for i=1:numel(BuildingSurfaces)
    building.surfaces(i).name=BuildingSurfaces{i};
    building.surfaces(i).zone_name=eval(['dat.BuildingSurface_0x3A_Detailed.' building.surfaces(i).name '.zone_name']);
    building.surfaces(i).surface_type=eval(['dat.BuildingSurface_0x3A_Detailed.' building.surfaces(i).name '.surface_type']);
    building.surfaces(i).outside_boundary=eval(['dat.BuildingSurface_0x3A_Detailed.' building.surfaces(i).name '.outside_boundary_condition']);
    building.surfaces(i).construction_name=eval(['dat.BuildingSurface_0x3A_Detailed.' building.surfaces(i).name '.construction_name']);
    building.surfaces(i).num_of_vertices=eval(['dat.BuildingSurface_0x3A_Detailed.' building.surfaces(i).name '.number_of_vertices']);
    
    %    eval(['dat.Material.' building.material(i).name '.conductivity']); 
    %    find(strcmp({building.material.tag}, building.zonednames{i})==1)
    
    % calculate Area of a surface using vertices
    for j=1:building.surfaces(i).num_of_vertices
    X(j)=eval(['dat.BuildingSurface_0x3A_Detailed.' building.surfaces(i).name '.vertices{1,' num2str(j) '}.vertex_x_coordinate']); 
    Y(j)=eval(['dat.BuildingSurface_0x3A_Detailed.' building.surfaces(i).name '.vertices{1,' num2str(j) '}.vertex_y_coordinate']);
    Z(j)=eval(['dat.BuildingSurface_0x3A_Detailed.' building.surfaces(i).name '.vertices{1,' num2str(j) '}.vertex_z_coordinate']);
    end
    if range(Y) == 0
        % vertical (floor/celling)
        building.surfaces(i).surface_area = polyarea(X,Z);
        %plot(X,Z,'*')
    elseif range(Z) == 0
        % horizontal (wall)
        building.surfaces(i).surface_area = polyarea(X,Y);   
        %plot(X,Y,'*')
    else
      % horizontal (wall)
        building.surfaces(i).surface_area = polyarea(Y,Z);   
        %plot(Y,Z,'*')
    end
    
    
end


 sum_ext=0;
    for zone=1:numel(building.zonenames)
        k=1;
        ext_id = 1;
        sum_int(zone)=0;
        sum_ext(zone)=0;
        % interzone
        for i=1:numel(BuildingSurfaces)
            if isequal(building.surfaces(i).construction_name,'int_wall')
                ind=find(strcmp({building.construction.name}, 'int_wall')==1);
                building.surfaces(i).outside_object=eval(['dat.BuildingSurface_0x3A_Detailed.' building.surfaces(i).name '.outside_boundary_condition_object']);
                if strcmp(building.surfaces(i).zone_name,building.zonenames{zone})
                   sum_int(zone)=sum_int(zone) + building.surfaces(i).surface_area*building.construction(ind).Uvalue;
                   building.zone(zone).a_int(k)=building.surfaces(i).surface_area*building.construction(ind).Uvalue;
                   % change with the general case
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_1','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_2','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_3','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_4','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_5','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_6','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_7','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_8','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end 
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_9','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end 
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_10','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end 
                   temp=strrep(building.surfaces(i).outside_object,'_ZN_1_FLR_1_Wall_11','');
                   if ~isequal(building.surfaces(i).outside_object,temp)
                   building.zone(zone).neighbour{k}=temp;
                   %continue
                   end 
                   k=k+1;
                end
            end
            building.zone(zone).a_ij=sum_int(zone);
            % external
%             if isequal(building.surfaces(i).construction_name,'nonres_ext_wall')
%                 ind=find(strcmp({building.construction.name}, 'nonres_ext_wall')==1);
%                 if strcmp(building.surfaces(i).zone_name,building.zonenames{zone})
%                     sum_ext(zone)=sum_ext(zone)+ building.surfaces(i).surface_area*building.construction(ind).Uvalue;
%                 end
%             end
%             building.zone(zone).a_z=sum_ext(zone);
            if isequal(building.surfaces(i).construction_name,'nonres_ext_wall')
                ind=find(strcmp({building.construction.name}, 'nonres_ext_wall')==1);
                if (strcmp(building.surfaces(i).zone_name,building.zonenames{zone}))
                    building.zone(zone).a_z(ext_id)= building.surfaces(i).surface_area*building.construction(ind).Uvalue;
                    ext_id = ext_id + 1;
                end
            end
            
            % roof
            if isequal(building.surfaces(i).construction_name,'nonres_roof')
                ind=find(strcmp({building.construction.name}, 'nonres_roof')==1);
                if strcmp(building.surfaces(i).zone_name,building.zonenames{zone})
                    building.zone(zone).a_roof=building.surfaces(i).surface_area*building.construction(ind).Uvalue;
                end
            end
            % floor
            if isequal(building.surfaces(i).construction_name,'ext_slab_6in_with_carpet')
                ind=find(strcmp({building.construction.name}, 'ext_slab_6in_with_carpet')==1);
                if strcmp(building.surfaces(i).zone_name,building.zonenames{zone})
                    building.zone(zone).a_floor=building.surfaces(i).surface_area*building.construction(ind).Uvalue;
                end
            end
        end
        
    end

% Find the number of fields with a specific fieldname
% if sum(strcmp(fieldnames(STRUCTURE), 'FIELDNAME')) == 1
%   do something
% end
