import json
import uuid
import os
import errno
import sys
from msa_sdk import constants
from msa_sdk.order import Order
from msa_sdk.variables import Variables
from msa_sdk.msa_api import MSA_API

dev_var = Variables()
dev_var.add('vnfd_name', var_type='String')
context = Variables.task_call(dev_var)
#filename is created based-on the device external reference
uuid_gen = str(uuid.uuid4())
if 'uuid_gen' in context:
	# if the uuid_gen exists in the context do not create a new one. This allow to update the existing NSD.
    name = context.get('vnfd_name')
    if name:
        vnfd_name = name + '_' + uuid_gen

#store in the context for the next process tasks usage.
context.update(uuid_gen=uuid_gen)

vnfd_name = uuid_gen
if 'vnfd_name' in context:
    vnfd_name = context.get('vnfd_name') + '_' + uuid_gen

vnfd_sol0001_schema = 'etsi_nfv_sol001_vnfd_types.yaml'
filename = '/opt/fmc_repository/Datafiles/NFV/VNFD/' + vnfd_name + '/Definition/' + vnfd_sol0001_schema

#vnfd_sol0001_schema content

etsi_nfv_sol001_vnfd_types = """
tosca_definitions_version: tosca_simple_yaml_1_2
description: ETSI NFV SOL 001 vnfd types definitions version 2.6.1
metadata:
  template_name: etsi_nfv_sol001_vnfd_types
  template_author: ETSI_NFV
  template_version: 2.6.1

imports:
  - https://forge.etsi.org/rep/nfv/SOL001/raw/v2.6.1/etsi_nfv_sol001_common_types.yaml

data_types:
  tosca.datatypes.nfv.VirtualNetworkInterfaceRequirements:
    derived_from: tosca.datatypes.Root
    description: Describes requirements on a virtual network interface
    properties:
      name:
        type: string
        description: Provides a human readable name for the requirement.
        required: false
      description:
        type: string
        description: Provides a human readable description of the requirement.
        required: false
      support_mandatory:
        type: boolean
        description: Indicates whether fulfilling the constraint is mandatory (TRUE) for successful operation or desirable (FALSE).
        required: true
      network_interface_requirements:
        type: map
        description: The network interface requirements. A map of strings that contain a set of key-value pairs that describes the hardware platform specific  network interface deployment requirements.
        required: true
        entry_schema:
          type: string
      nic_io_requirements:
        type: tosca.datatypes.nfv.LogicalNodeData
        description: references (couples) the CP with any logical node I/O requirements (for network devices) that may have been created. Linking these attributes is necessary so that so that I/O requirements that need to be articulated at the logical node level can be associated with the network interface requirements associated with the CP.
        required: false

  tosca.datatypes.nfv.RequestedAdditionalCapability:
    derived_from: tosca.datatypes.Root
    description: describes requested additional capability for a particular VDU
    properties:
      requested_additional_capability_name:
        type: string
        description: Identifies a requested additional capability for the VDU.
        required: true
      support_mandatory:
        type: boolean
        description: Indicates whether the requested additional capability is mandatory for successful operation.
        required: true
      min_requested_additional_capability_version:
        type: string
        description: Identifies the minimum version of the requested additional capability.
        required: false
      preferred_requested_additional_capability_version:
        type: string
        description: Identifies the preferred version of the requested additional capability.
        required: false
      target_performance_parameters:
        type: map
        description: Identifies specific attributes, dependent on the requested additional capability type.
        required: true
        entry_schema:
          type: string

  tosca.datatypes.nfv.VirtualMemory:
    derived_from: tosca.datatypes.Root
    description: supports the specification of requirements related to virtual memory of a virtual compute resource
    properties:
      virtual_mem_size:
        type: scalar-unit.size
        description: Amount of virtual memory.
        required: true
      virtual_mem_oversubscription_policy:
        type: string
        description: The memory core oversubscription policy in terms of virtual memory to physical memory on the platform.
        required: false
      vdu_mem_requirements:
        type: map
        description: The hardware platform specific VDU memory requirements. A map of strings that contains a set of key-value pairs that describes hardware platform specific VDU memory requirements.
        required: false
        entry_schema:
          type: string
      numa_enabled:
        type: boolean
        description: It specifies the memory allocation to be cognisant of the relevant process/core allocation.
        required: false
        default: false

  tosca.datatypes.nfv.VirtualCpu:
    derived_from: tosca.datatypes.Root
    description: Supports the specification of requirements related to virtual CPU(s) of a virtual compute resource
    properties:
      cpu_architecture:
        type: string
        description: CPU architecture type. Examples are x86, ARM
        required: false
      num_virtual_cpu:
        type: integer
        description: Number of virtual CPUs
        required: true
        constraints:
          - greater_than: 0
      virtual_cpu_clock:
        type: scalar-unit.frequency
        description: Minimum virtual CPU clock rate
        required: false
      virtual_cpu_oversubscription_policy:
        type: string
        description: CPU core oversubscription policy e.g. the relation of virtual CPU cores to physical CPU cores/threads.
        required: false
      vdu_cpu_requirements:
        type: map
        description: The hardware platform specific VDU CPU requirements. A map of strings that contains a set of key-value pairs describing VDU CPU specific hardware platform requirements.
        required: false
        entry_schema:
          type: string
      virtual_cpu_pinning:
        type: tosca.datatypes.nfv.VirtualCpuPinning
        description: The virtual CPU pinning configuration for the virtualised compute resource.
        required: false

  tosca.datatypes.nfv.VirtualCpuPinning:
    derived_from: tosca.datatypes.Root
    description: Supports the specification of requirements related to the virtual CPU pinning configuration of a virtual compute resource
    properties:
      virtual_cpu_pinning_policy:
        type: string
        description: 'Indicates the policy for CPU pinning. The policy can take values of "static" or "dynamic". In case of "dynamic" the allocation of virtual CPU cores to logical CPU cores is decided by the VIM. (e.g.: SMT (Simultaneous Multi-Threading) requirements). In case of "static" the allocation is requested to be according to the virtual_cpu_pinning_rule.'
        required: false
        constraints:
          - valid_values: [ static, dynamic ]
      virtual_cpu_pinning_rule:
        type: list
        description: Provides the list of rules for allocating virtual CPU cores to logical CPU cores/threads
        required: false
        entry_schema:
          type: string

  tosca.datatypes.nfv.VnfcConfigurableProperties:
    derived_from: tosca.datatypes.Root
    description: Defines the configurable properties of a VNFC
    # properties:
      # additional_vnfc_configurable_properties:
      #   type: tosca.datatypes.nfv.VnfcAdditionalConfigurableProperties
      #   description: Describes additional configuration for VNFC that
      #   can be modified using the ModifyVnfInfo operation
      #   required: false
      # derived types are expected to introduce
      # additional_vnfc_configurable_properties with its type derived from
      # tosca.datatypes.nfv.VnfcAdditionalConfigurableProperties

  tosca.datatypes.nfv.VnfcAdditionalConfigurableProperties:
    derived_from: tosca.datatypes.Root
    description: VnfcAdditionalConfigurableProperties type is an empty base type for deriving data types for describing additional configurable properties for a given VNFC.

  tosca.datatypes.nfv.VduProfile:
    derived_from: tosca.datatypes.Root
    description: describes additional instantiation data for a given Vdu.Compute used in a specific deployment flavour.
    properties:
      min_number_of_instances:
        type: integer
        description: Minimum number of instances of the VNFC based on this Vdu.Compute that is permitted to exist for a particular VNF deployment flavour.
        required: true
        constraints:
          - greater_or_equal: 0
      max_number_of_instances:
        type: integer
        description: Maximum number of instances of the VNFC based on this Vdu.Compute that is permitted to exist for a particular VNF deployment flavour.
        required: true
        constraints:
          - greater_or_equal: 0

  tosca.datatypes.nfv.VlProfile:
    derived_from: tosca.datatypes.Root
    description: Describes additional instantiation data for a given VL used in a specific VNF deployment flavour.
    properties:
      max_bitrate_requirements:
        type: tosca.datatypes.nfv.LinkBitrateRequirements
        description: Specifies the maximum bitrate requirements for a VL instantiated according to this profile.
        required: true
      min_bitrate_requirements:
        type: tosca.datatypes.nfv.LinkBitrateRequirements
        description: Specifies the minimum bitrate requirements for a VL instantiated according to this profile.
        required: true
      qos:
        type: tosca.datatypes.nfv.Qos
        description: Specifies the QoS requirements of a VL instantiated according to this profile.
        required: false
      virtual_link_protocol_data:
        type: list
        description: Specifies the protocol data for a virtual link.
        required: false
        entry_schema:
          type: tosca.datatypes.nfv.VirtualLinkProtocolData

  tosca.datatypes.nfv.VirtualLinkProtocolData:
    derived_from: tosca.datatypes.Root
    description: describes one protocol layer and associated protocol data for a given virtual link used in a specific VNF deployment flavour
    properties:
      associated_layer_protocol:
         type: string
         description: Identifies one of the protocols a virtualLink gives access to (ethernet, mpls, odu2, ipv4, ipv6, pseudo-wire) as specified by the connectivity_type property.
         required: true
         constraints:
           - valid_values: [ ethernet, mpls, odu2, ipv4, ipv6, pseudo-wire ]
      l2_protocol_data:
         type: tosca.datatypes.nfv.L2ProtocolData
         description: Specifies the L2 protocol data for a virtual link. Shall be present when the associatedLayerProtocol attribute indicates a L2 protocol and shall be absent otherwise.
         required: false
      l3_protocol_data:
         type: tosca.datatypes.nfv.L3ProtocolData
         description: Specifies the L3 protocol data for this virtual link.  Shall be present when the associatedLayerProtocol attribute indicates a L3 protocol and shall be absent otherwise.
         required: false

  tosca.datatypes.nfv.L2ProtocolData:
    derived_from: tosca.datatypes.Root
    description: describes L2 protocol data for a given virtual link used in a specific VNF deployment flavour.
    properties:
      name:
        type: string
        description: Identifies the network name associated with this L2 protocol.
        required: false
      network_type:
        type: string
        description: Specifies the network type for this L2 protocol.The value may be overridden at run-time.
        required: false
        constraints:
          - valid_values: [ flat, vlan, vxlan, gre ]
      vlan_transparent:
        type: boolean
        description: Specifies whether to support VLAN transparency for this L2 protocol or not.
        required: false
        default: false
      mtu:
        type: integer
        description: Specifies the maximum transmission unit (MTU) value for this L2 protocol.
        required: false
        constraints:
          - greater_than: 0

  tosca.datatypes.nfv.L3ProtocolData:
    derived_from: tosca.datatypes.Root
    description: describes L3 protocol data for a given virtual link used in a specific VNF deployment flavour.
    properties:
      name:
        type: string
        description: Identifies the network name associated with this L3 protocol.
        required: false
      ip_version:
        type: string
        description: Specifies IP version of this L3 protocol.The value of the ip_version property shall be consistent with the value of the layer_protocol in the connectivity_type property of the virtual link node.
        required: true
        constraints:
          - valid_values: [ ipv4, ipv6 ]
      cidr:
        type: string
        description: Specifies the CIDR (Classless Inter-Domain Routing) of this L3 protocol. The value may be overridden at run-time.
        required: true
      ip_allocation_pools:
        type: list
        description: Specifies the allocation pools with start and end IP addresses for this L3 protocol. The value may be overridden at run-time.
        required: false
        entry_schema:
          type: tosca.datatypes.nfv.IpAllocationPool
      gateway_ip:
        type: string
        description: Specifies the gateway IP address for this L3 protocol. The value may be overridden at run-time.
        required: false
      dhcp_enabled:
        type: boolean
        description: Indicates whether DHCP (Dynamic Host Configuration Protocol) is enabled or disabled for this L3 protocol. The value may be overridden at run-time.
        required: false
      ipv6_address_mode:
        type: string
        description: Specifies IPv6 address mode. May be present when the value of the ipVersion attribute is "ipv6" and shall be absent otherwise. The value may be overridden at run-time.
        required: false
        constraints:
          - valid_values: [ slaac, dhcpv6-stateful, dhcpv6-stateless ]

  tosca.datatypes.nfv.IpAllocationPool:
    derived_from: tosca.datatypes.Root
    description: Specifies a range of IP addresses
    properties:
      start_ip_address:
        type: string
        description: The IP address to be used as the first one in a pool of addresses derived from the cidr block full IP range
        required: true
      end_ip_address:
        type: string
        description: The IP address to be used as the last one in a pool of addresses derived from the cidr block full IP range
        required: true

  tosca.datatypes.nfv.InstantiationLevel:
    derived_from: tosca.datatypes.Root
    description: Describes the scale level for each aspect that corresponds to a given level of resources to be instantiated within a deployment flavour in term of the number VNFC instances
    properties:
      description:
        type: string
        description: Human readable description of the level
        required: true
      scale_info:
        type: map # key: aspectId
        description: Represents for each aspect the scale level that corresponds to this instantiation level. scale_info shall be present if the VNF supports scaling.
        required: false
        entry_schema:
          type: tosca.datatypes.nfv.ScaleInfo

  tosca.datatypes.nfv.VduLevel:
    derived_from: tosca.datatypes.Root
    description: Indicates for a given Vdu.Compute in a given level the number of instances to deploy
    properties:
      number_of_instances:
        type: integer
        description: Number of instances of VNFC based on this VDU to deploy for this level.
        required: true
        constraints:
          - greater_or_equal: 0

  tosca.datatypes.nfv.VnfLcmOperationsConfiguration:
    derived_from: tosca.datatypes.Root
    description: Represents information to configure lifecycle management operations
    properties:
      instantiate:
        type: tosca.datatypes.nfv.VnfInstantiateOperationConfiguration
        description: Configuration parameters for the InstantiateVnf operation
        required: false
      scale:
        type: tosca.datatypes.nfv.VnfScaleOperationConfiguration
        description: Configuration parameters for the ScaleVnf operation
        required: false
      scale_to_level:
        type: tosca.datatypes.nfv.VnfScaleToLevelOperationConfiguration
        description: Configuration parameters for the ScaleVnfToLevel operation
        required: false
      change_flavour:
        type: tosca.datatypes.nfv.VnfChangeFlavourOperationConfiguration
        description: Configuration parameters for the changeVnfFlavourOpConfig operation
        required: false
      heal:
        type: tosca.datatypes.nfv.VnfHealOperationConfiguration
        description: Configuration parameters for the HealVnf operation
        required: false
      terminate:
        type: tosca.datatypes.nfv.VnfTerminateOperationConfiguration
        description: Configuration parameters for the TerminateVnf operation
        required: false
      operate:
        type: tosca.datatypes.nfv.VnfOperateOperationConfiguration
        description: Configuration parameters for the OperateVnf operation
        required: false
      change_ext_connectivity:
        type:   tosca.datatypes.nfv.VnfChangeExtConnectivityOperationConfiguration
        description: Configuration parameters for the changeExtVnfConnectivityOpConfig operation
        required: false

  tosca.datatypes.nfv.VnfInstantiateOperationConfiguration:
    derived_from: tosca.datatypes.Root
    description: represents information that affect the invocation of the InstantiateVnf operation.

  tosca.datatypes.nfv.VnfScaleOperationConfiguration:
    derived_from: tosca.datatypes.Root
    description: Represents information that affect the invocation of the ScaleVnf operation
    properties:
      scaling_by_more_than_one_step_supported:
        type: boolean
        description: Signals whether passing a value larger than one in the numScalingSteps parameter of the ScaleVnf operation is supported by this VNF.
        required: false
        default: false

  tosca.datatypes.nfv.VnfScaleToLevelOperationConfiguration:
    derived_from: tosca.datatypes.Root
    description: represents information that affect the invocation of the ScaleVnfToLevel operation
    properties:
      arbitrary_target_levels_supported:
        type: boolean
        description: Signals whether scaling according to the parameter "scaleInfo" is supported by this VNF
        required: true

  tosca.datatypes.nfv.VnfHealOperationConfiguration:
    derived_from: tosca.datatypes.Root
    description: represents information that affect the invocation of the HealVnf operation
    properties:
      causes:
        type: list
        description: Supported "cause" parameter values
        required: false
        entry_schema:
          type: string

  tosca.datatypes.nfv.VnfTerminateOperationConfiguration:
    derived_from: tosca.datatypes.Root
    description: represents information that affect the invocation of the TerminateVnf
    properties:
      min_graceful_termination_timeout:
        type: scalar-unit.time
        description: Minimum timeout value for graceful termination of a VNF instance
        required: true
      max_recommended_graceful_termination_timeout:
        type: scalar-unit.time
        description: Maximum recommended timeout value that can be needed to gracefully terminate a VNF instance of a particular type under certain conditions, such as maximum load condition. This is provided by VNF provider as information for the operator facilitating the selection of optimal timeout value. This value is not used as constraint
        required: false

  tosca.datatypes.nfv.VnfOperateOperationConfiguration:
    derived_from: tosca.datatypes.Root
    description: represents information that affect the invocation of the OperateVnf operation
    properties:
      min_graceful_stop_timeout:
        type: scalar-unit.time
        description: Minimum timeout value for graceful stop of a VNF instance
        required: true
      max_recommended_graceful_stop_timeout:
        type: scalar-unit.time
        description: Maximum recommended timeout value that can be needed to gracefully stop a VNF instance of a particular type under certain conditions, such as maximum load condition. This is provided by VNF provider as information for the operator facilitating the selection of optimal timeout value. This value is not used as constraint
        required: false

  tosca.datatypes.nfv.ScaleInfo:
    derived_from: tosca.datatypes.Root
    description: Indicates for a given scaleAspect the corresponding scaleLevel
    properties:
      scale_level:
        type: integer
        description: The scale level for a particular aspect
        required: true
        constraints:
          - greater_or_equal: 0

  tosca.datatypes.nfv.ScalingAspect:
    derived_from: tosca.datatypes.Root
    properties:
      name:
        type: string
        required: true
      description:
        type: string
        required: true
      max_scale_level:
        type: integer # positiveInteger
        required: true
        constraints:
          - greater_or_equal: 0
      step_deltas:
        type: list
        required: false
        entry_schema:
          type: string # Identifier

  tosca.datatypes.nfv.VnfConfigurableProperties:
    derived_from: tosca.datatypes.Root
    description: indicates configuration properties for a given VNF (e.g. related to auto scaling and auto healing).
    properties:
      is_autoscale_enabled:
        type: boolean
        description: It permits to enable (TRUE)/disable (FALSE) the auto-scaling functionality. If the properties is not present for configuring, then VNF property is not supported
        required: false
      is_autoheal_enabled:
        type: boolean
        description: It permits to enable (TRUE)/disable (FALSE) the auto-healing functionality. If the properties is not present for configuring, then VNF property is not supported
        required: false
      # additional_configurable_properties:
        # description: It provides VNF specific configurable properties that
        # can be modified using the ModifyVnfInfo operation
        # required: false
        # type: tosca.datatypes.nfv.VnfAdditionalConfigurableProperties
      # derived types are expected to introduce
      # additional_configurable_properties with its type derived from
      # tosca.datatypes.nfv.VnfAdditionalConfigurableProperties

  tosca.datatypes.nfv.VnfAdditionalConfigurableProperties:
    derived_from: tosca.datatypes.Root
    description: is an empty base type for deriving data types for describing additional configurable properties for a given VNF

  tosca.datatypes.nfv.VnfInfoModifiableAttributes:
    derived_from: tosca.datatypes.Root
    description: Describes VNF-specific extension and metadata for a given VNF
    #properties:
      #extensions:
        #type: tosca.datatypes.nfv.VnfInfoModifiableAttributesExtensions
        #description: "Extension" properties of VnfInfo that are writeable
        #required: false
        # derived types are expected to introduce
        # extensions with its type derived from
        # tosca.datatypes.nfv.VnfInfoModifiableAttributesExtensions
      #metadata:
        #type: tosca.datatypes.nfv.VnfInfoModifiableAttributesMetadata
        #description: "Metadata" properties of VnfInfo that are writeable
        #required: false
        # derived types are expected to introduce
        # metadata with its type derived from
        # tosca.datatypes.nfv.VnfInfoModifiableAttributesMetadata

  tosca.datatypes.nfv.VnfInfoModifiableAttributesExtensions:
    derived_from: tosca.datatypes.Root
    description: is an empty base type for deriving data types for describing VNF-specific extension

  tosca.datatypes.nfv.VnfInfoModifiableAttributesMetadata:
    derived_from: tosca.datatypes.Root
    description: is an empty base type for deriving data types for describing VNF-specific metadata

  tosca.datatypes.nfv.LogicalNodeData:
    derived_from: tosca.datatypes.Root
    description: Describes compute, memory and I/O requirements associated with a particular VDU.
    properties:
      logical_node_requirements:
        type: map
        description: The logical node-level compute, memory and I/O requirements. A map  of strings that contains a set of key-value pairs that describes hardware platform specific deployment requirements, including the number of CPU cores on this logical node, a memory configuration specific to a logical node  or a requirement related to the association of an I/O device with the logical node.
        required: false
        entry_schema:
          type: string

  tosca.datatypes.nfv.SwImageData:
    derived_from: tosca.datatypes.Root
    description: describes information  related to a software image artifact
    properties: # in SOL001 v0.8.0: "properties or metadata:"
      name:
        type: string
        description: Name of this software image
        required: true
      version:
        type: string
        description: Version of this software image
        required: true
      checksum:
        type: tosca.datatypes.nfv.ChecksumData
        description:  Checksum of the software image file
        required: true
      container_format:
        type: string
        description: The container format describes the container file format in which software image is provided
        required: true
        constraints:
          - valid_values: [ aki, ami, ari, bare, docker, ova, ovf ]
      disk_format:
        type: string
        description: The disk format of a software image is the format of the underlying disk image
        required: true
        constraints:
          - valid_values: [ aki, ami, ari, iso, qcow2, raw, vdi, vhd, vhdx, vmdk ]
      min_disk:
        type: scalar-unit.size # Number
        description:  The minimal disk size requirement for this software image
        required: true
        constraints:
          - greater_or_equal: 0 B
      min_ram:
        type: scalar-unit.size # Number
        description: The minimal RAM requirement for this software image
        required: false
        constraints:
          - greater_or_equal: 0 B
      size:
        type: scalar-unit.size # Number
        description: The size of this software image
        required: true
      operating_system:
        type: string
        description: Identifies the operating system used in the software image
        required: false
      supported_virtualisation_environments:
        type: list
        description: Identifies the virtualisation environments (e.g. hypervisor) compatible with this software image
        required: false
        entry_schema:
          type: string

  tosca.datatypes.nfv.VirtualBlockStorageData:
    derived_from: tosca.datatypes.Root
    description: VirtualBlockStorageData describes block storage requirements associated with compute resources in a particular VDU, either as a local disk or as virtual attached storage
    properties:
      size_of_storage:
        type: scalar-unit.size
        description: Size of virtualised storage resource
        required: true
        constraints:
          - greater_or_equal: 0 B
      vdu_storage_requirements:
        type: map
        description: The hardware platform specific storage requirements. A map of strings that contains a set of key-value pairs that represents the hardware platform specific storage deployment requirements.
        required: false
        entry_schema:
          type: string
      rdma_enabled:
        type: boolean
        description: Indicates if the storage support RDMA
        required: false
        default: false

  tosca.datatypes.nfv.VirtualObjectStorageData:
      derived_from: tosca.datatypes.Root
      description: VirtualObjectStorageData describes object storage requirements associated with compute resources in a particular VDU
      properties:
        max_size_of_storage:
          type: scalar-unit.size
          description: Maximum size of virtualized storage resource
          required: false
          constraints:
            - greater_or_equal: 0 B

  tosca.datatypes.nfv.VirtualFileStorageData:
      derived_from: tosca.datatypes.Root
      description: VirtualFileStorageData describes file storage requirements associated with compute resources in a particular VDU
      properties:
        size_of_storage:
          type: scalar-unit.size
          description: Size of virtualized storage resource
          required: true
          constraints:
            - greater_or_equal: 0 B
        file_system_protocol:
          type: string
          description: The shared file system protocol (e.g. NFS, CIFS)
          required: true

  tosca.datatypes.nfv.VirtualLinkBitrateLevel:
    derived_from: tosca.datatypes.Root
    description: Describes bitrate requirements applicable to the virtual link instantiated from a particicular VnfVirtualLink
    properties:
      bitrate_requirements:
        type: tosca.datatypes.nfv.LinkBitrateRequirements
        description: Virtual link bitrate requirements for an instantiation level or bitrate delta for a scaling step
        required: true

  tosca.datatypes.nfv.VnfOperationAdditionalParameters:
    derived_from: tosca.datatypes.Root
    description: Is an empty base type for deriving data type for describing VNF-specific parameters to be passed when invoking lifecycle management operations
    #properties:

  tosca.datatypes.nfv.VnfChangeFlavourOperationConfiguration:
    derived_from: tosca.datatypes.Root
    description: represents information that affect the invocation of the ChangeVnfFlavour operation
    #properties:

  tosca.datatypes.nfv.VnfChangeExtConnectivityOperationConfiguration:
    derived_from: tosca.datatypes.Root
    description: represents information that affect the invocation of the ChangeExtVnfConnectivity operation
    #properties:

  tosca.datatypes.nfv.VnfMonitoringParameter:
    derived_from: tosca.datatypes.Root
    description: Represents information on virtualised resource related performance metrics applicable to the VNF.
    properties:
      name:
        type: string
        description: Human readable name of the monitoring parameter
        required: true
      performance_metric:
        type: string
        description: Identifies the performance metric, according to ETSI GS NFV-IFA 027.
        required: true
        constraints:
          - valid_values: [ v_cpu_usage_mean_vnf, v_cpu_usage_peak_vnf, v_memory_usage_mean_vnf, v_memory_usage_peak_vnf, v_disk_usage_mean_vnf, v_disk_usage_peak_vnf, byte_incoming_vnf_ext_cp, byte_outgoing_vnf_ext_cp,
packet_incoming_vnf_ext_cp, packet_outgoing_vnf_ext_cp ]
      collection_period:
        type: scalar-unit.time
        description: Describes the periodicity at which to collect the performance information.
        required: false
        constraints:
          - greater_than: 0 s

  tosca.datatypes.nfv.VnfcMonitoringParameter:
    derived_from: tosca.datatypes.Root
    description: Represents information on virtualised resource related performance metrics applicable to the VNF.
    properties:
      name:
        type: string
        description: Human readable name of the monitoring parameter
        required: true
      performance_metric:
        type: string
        description: Identifies the performance metric, according to ETSI GS NFV-IFA 027.
        required: true
        constraints:
          - valid_values: [ v_cpu_usage_mean_vnf, v_cpu_usage_peak_vnf, v_memory_usage_mean_vnf, v_memory_usage_peak_vnf, v_disk_usage_mean_vnf, v_disk_usage_peak_vnf, byte_incoming_vnf_int_cp, byte_outgoing_vnf_int_cp, packet_incoming_vnf_int_cp, packet_outgoing_vnf_int_cp ]
      collection_period:
        type: scalar-unit.time
        description: Describes the periodicity at which to collect the performance information.
        required: false
        constraints:
          - greater_than: 0 s

  tosca.datatypes.nfv.VirtualLinkMonitoringParameter:
    derived_from: tosca.datatypes.Root
    description: Represents information on virtualised resource related performance metrics applicable to the VNF.
    properties:
      name:
        type: string
        description: Human readable name of the monitoring parameter
        required: true
      performance_metric:
        type: string
        description: Identifies a performance metric derived from those defined in ETSI GS NFV-IFA 027.The packetOutgoingVirtualLink and packetIncomingVirtualLink metrics shall be obtained by aggregation the PacketOutgoing and PacketIncoming measurements defined in clause 7.1 of GS NFV-IFA 027 of all virtual link ports attached to the virtual link to which the metrics apply.
        required: true
        constraints:
           - valid_values: [ packet_outgoing_virtual_link, packet_incoming_virtual_link ]
      collection_period:
        type: scalar-unit.time
        description: Describes the periodicity at which to collect the performance information.
        required: false
        constraints:
          - greater_than: 0 s

  tosca.datatypes.nfv.InterfaceDetails:
    derived_from: tosca.datatypes.Root
    description: information used to access an interface exposed by a VNF
    properties:
      uri_components:
        type: tosca.datatypes.nfv.UriComponents
        description: Provides components to build a Uniform Ressource Identifier (URI) where to access the interface end point.
        required: false
      interface_specific_data:
        type: map
        description: Provides additional details that are specific to the type of interface considered.
        required: false
        entry_schema:
          type: string

  tosca.datatypes.nfv.UriComponents:
    derived_from: tosca.datatypes.Root
    description: information used to build a URI that complies with IETF RFC 3986 [8].
    properties:
      scheme:
        type: string # shall comply with IETF RFC3986
        description: scheme component of a URI.
        required: true
      authority:
        type: tosca.datatypes.nfv.UriAuthority
        description: Authority component of a URI
        required: false
      path:
        type: string # shall comply with IETF RFC 3986
        description: path component of a URI.
        required: false
      query:
        type: string # shall comply with IETF RFC 3986
        description: query component of a URI.
        required: false
      fragment:
        type: string # shall comply with IETF RFC 3986
        description: fragment component of a URI.
        required: false

  tosca.datatypes.nfv.UriAuthority:
    derived_from: tosca.datatypes.Root
    description: information that corresponds to the authority component of a URI as specified in IETF RFC 3986 [8]
    properties:
      user_info:
        type: string # shall comply with IETF RFC 3986
        description: user_info field of the authority component of a URI
        required: false
      host:
        type: string # shall comply with IETF RFC 3986
        description: host field of the authority component of a URI
        required: false
      port:
        type: string # shall comply with IETF RFC 3986
        description: port field of the authority component of a URI
        required: false

  tosca.datatypes.nfv.ChecksumData:
    derived_from: tosca.datatypes.Root
    description: Describes information about the result of performing a checksum operation over some arbitrary data
    properties:
      algorithm:
        type: string
        description: Describes the algorithm used to obtain the checksum value
        required: true
        constraints:
          - valid_values: [sha-224, sha-256, sha-384, sha-512 ]
      hash:
        type: string
        description: Contains the result of applying the algorithm indicated by the algorithm property to the data to which this ChecksumData refers
        required: true

artifact_types:
  tosca.artifacts.nfv.SwImage:
    derived_from: tosca.artifacts.Deployment.Image
    description: describes the software image which is directly loaded on the virtualisation container realizing of the VDU or is to be loaded on a virtual storage resource.
    properties:
      name:
        type: string
        required: true
      version:
        type: string
        required: true
      checksum:
        type: string
        required: true
      container_format:
        type: string
        required: true
      disk_format:
        type: string
        required: true
      min_disk:
        type: scalar-unit.size # Number
        required: true
      min_ram:
        type: scalar-unit.size # Number
        required: false
      size:
        type: scalar-unit.size # Number
        required: true
      sw_image:
        type: string
        required: true
      operating_system:
        type: string
        required: false
      supported_virtualisation_environments:
        type: list
        entry_schema:
          type: string
        required: false

  tosca.artifacts.Implementation.nfv.Mistral:
    derived_from: tosca.artifacts.Implementation
    description: artifacts for Mistral workflows
    mime_type: application/x-yaml
    file_ext: [ yaml ]

capability_types:
  tosca.capabilities.nfv.VirtualBindable:
    derived_from: tosca.capabilities.Node
    description: Indicates that the node that includes it can be pointed by a tosca.relationships.nfv.VirtualBindsTo relationship type which is used to model the VduHasCpd association

  tosca.capabilities.nfv.VirtualCompute:
    derived_from: tosca.capabilities.Node
    description: Describes the capabilities related to virtual compute resources
    properties:
      logical_node:
        type: map
        description: Describes the Logical Node requirements
        required: false
        entry_schema:
           type: tosca.datatypes.nfv.LogicalNodeData
      requested_additional_capabilities:
        type: map
        description: Describes additional capability for a particular VDU
        required: false
        entry_schema:
           type: tosca.datatypes.nfv.RequestedAdditionalCapability
      compute_requirements:
        type: map
        required: false
        entry_schema:
           type: string
      virtual_memory:
        type: tosca.datatypes.nfv.VirtualMemory
        description: Describes virtual memory of the virtualized compute
        required: true
      virtual_cpu:
        type: tosca.datatypes.nfv.VirtualCpu
        description: Describes virtual CPU(s) of the virtualized compute
        required: true
      virtual_local_storage:
        type: list
        description: A list of virtual system disks created and destroyed as part of the VM lifecycle
        required: false
        entry_schema:
          type: tosca.datatypes.nfv.VirtualBlockStorageData
          description: virtual system disk definition

  tosca.capabilities.nfv.VirtualStorage:
    derived_from: tosca.capabilities.Root
    description: Describes the attachment capabilities related to Vdu.Storage

relationship_types:
  tosca.relationships.nfv.VirtualBindsTo:
    derived_from: tosca.relationships.DependsOn
    description: Represents an association relationship between Vdu.Compute and VduCp node types
    valid_target_types: [ tosca.capabilities.nfv.VirtualBindable ]

  tosca.relationships.nfv.AttachesTo:
    derived_from: tosca.relationships.Root
    description: Represents an association relationship between the Vdu.Compute and one of the node types, Vdu.VirtualBlockStorage, Vdu.VirtualObjectStorage or Vdu.VirtualFileStorage
    valid_target_types: [ tosca.capabilities.nfv.VirtualStorage ]

interface_types:
  tosca.interfaces.nfv.Vnflcm:
    derived_from: tosca.interfaces.Root
    description: This interface encompasses a set of TOSCA operations corresponding to the VNF LCM operations defined in ETSI GS NFV-IFA 007 as well as to preamble and postamble procedures to the execution of the VNF LCM operations.
    instantiate:
      description: Invoked upon receipt of an Instantiate VNF request
      # inputs:
        # additional_parameters:
          # type: tosca.datatypes.nfv.VnfOperationAdditionalParameters
          # required: false
        # derived types are expected to introduce additional_parameters with
        # its type derived from
        # tosca.datatypes.nfv.VnfOperationAdditionalParameters
    instantiate_start:
      description: Invoked before instantiate
    instantiate_end:
      description: Invoked after instantiate
    terminate:
      description: Invoked upon receipt Terminate VNF request
      # inputs:
        # additional_parameters:
          # type: tosca.datatypes.nfv.VnfOperationAdditionalParameters
          # required: false
        # derived types are expected to introduce additional_parameters with
        # its type derived from
        # tosca.datatypes.nfv.VnfOperationAdditionalParameters
    terminate_start:
      description: Invoked before terminate
    terminate_end:
      description: Invoked after terminate
    modify_information:
      description: Invoked upon receipt of a Modify VNF Information request
    modify_information_start:
      description: Invoked before modify_information
    modify_information_end:
      description: Invoked after modify_information
    change_flavour:
      description: Invoked upon receipt of a Change VNF Flavour request
      # inputs:
        # additional_parameters:
          # type: tosca.datatypes.nfv.VnfOperationAdditionalParameters
          # required: false
        # derived types are expected to introduce additional_parameters with
        # its type derived from
        # tosca.datatypes.nfv.VnfOperationAdditionalParameters
    change_flavour_start:
      description: Invoked before change_flavour
    change_flavour_end:
      description: Invoked after change_flavour
    change_external_connectivity:
      description: Invoked upon receipt of a Change External VNF Connectivity   request
      # inputs:
        # additional_parameters:
          # type: tosca.datatypes.nfv.VnfOperationAdditionalParameters
          # required: false
        # derived types are expected to introduce additional_parameters with
        # its type derived from
        # tosca.datatypes.nfv.VnfOperationAdditionalParameters
    change_external_connectivity_start:
      description: Invoked before change_external_connectivity
    change_external_connectivity_end:
      description: Invoked after change_external_connectivity
    operate:
      description: Invoked upon receipt of an Operate VNF request
      # inputs:
        # additional_parameters:
          # type: tosca.datatypes.nfv.VnfOperationAdditionalParameters
          # required: false
        # derived types are expected to introduce additional_parameters with
        # its type derived from
        # tosca.datatypes.nfv.VnfOperationAdditionalParameters
    operate_start:
      description: Invoked before operate
    operate_end:
      description: Invoked after operate
    heal:
      description: Invoked upon receipt of a Heal VNF request
      # inputs:
        # additional_parameters:
          # type: tosca.datatypes.nfv.VnfOperationAdditionalParameters
          # required: false
        # derived types are expected to introduce additional_parameters with
        # its type derived from
        # tosca.datatypes.nfv.VnfOperationAdditionalParameters
    heal_start:
      description: Invoked before heal
    heal_end:
      description: Invoked after heal
    scale:
      description: Invoked upon receipt of a Scale VNF request
      # inputs:
        # additional_parameters:
          # type: tosca.datatypes.nfv.VnfOperationAdditionalParameters
          # required: false
        # derived types are expected to introduce additional_parameters with
        # its type derived from
        # tosca.datatypes.nfv.VnfOperationAdditionalParameters
    scale_start:
      description: Invoked before scale
    scale_end:
      description: Invoked after scale
    scale_to_level:
      description: Invoked upon receipt of a Scale VNF to Level request
      # inputs:
        # additional_parameters:
          # type: tosca.datatypes.nfv.VnfOperationAdditionalParameters
          # required: false
        # derived types are expected to introduce additional_parameters with
        # its type derived from
        # tosca.datatypes.nfv.VnfOperationAdditionalParameters
    scale_to_level_start:
      description: Invoked before scale_to_level
    scale_to_level_end:
      description: Invoked after scale_to_level

node_types:
  tosca.nodes.nfv.VNF:
    derived_from: tosca.nodes.Root
    description: The generic abstract type from which all VNF specific abstract node types shall be derived to form, together with other node types, the TOSCA service template(s) representing the VNFD
    properties:
      descriptor_id: # instead of vnfd_id
        type: string # GUID
        description: Globally unique identifier of the VNFD
        required: true
      descriptor_version: # instead of vnfd_version
        type: string
        description: Identifies the version of the VNFD
        required: true
      provider: # instead of vnf_provider
        type: string
        description: Provider of the VNF and of the VNFD
        required: true
      product_name: # instead of vnf_product_name
        type: string
        description: Human readable name for the VNF Product
        required: true
      software_version: # instead of vnf_software_version
        type: string
        description: Software version of the VNF
        required: true
      product_info_name: # instead of vnf_product_info_name
        type: string
        description: Human readable name for the VNF Product
        required: false
      product_info_description: # instead of vnf_product_info_description
        type: string
        description: Human readable description of the VNF Product
        required: false
      vnfm_info:
        type: list
        required: true
        description: Identifies VNFM(s) compatible with the VNF
        entry_schema:
          type: string
          constraints:
            - pattern: (^etsivnfm:v[0-9]?[0-9]\.[0-9]?[0-9]\.[0-9]?[0-9]$)|(^[0-9]+:[a-zA-Z0-9.-]+$)
      localization_languages:
        type: list
        description: Information about localization languages of the VNF
        required: false
        entry_schema:
          type: string #IETF RFC 5646 string
      default_localization_language:
        type: string #IETF RFC 5646 string
        description: Default localization language that is instantiated if no information about selected localization language is available
        required: false
      #configurable_properties:
        #type: tosca.datatypes.nfv.VnfConfigurableProperties
        #description: Describes the configurable properties of the VNF
        #required: false
        # derived types are expected to introduce configurable_properties
        # with its type derived from
        # tosca.datatypes.nfv.VnfConfigurableProperties
      #modifiable_attributes:
        #type: tosca.datatypes.nfv.VnfInfoModifiableAttributes
        #description: Describes the modifiable attributes of the VNF
        #required: false
        # derived types are expected to introduce modifiable_attributes
        # with its type derived from
        # tosca.datatypes.nfv.VnfInfoModifiableAttributes
      lcm_operations_configuration:
        type: tosca.datatypes.nfv.VnfLcmOperationsConfiguration
        description: Describes the configuration parameters for the VNF LCM operations
        required: false
      monitoring_parameters:
        type: list
        entry_schema:
          type: tosca.datatypes.nfv.VnfMonitoringParameter
        description: Describes monitoring parameters applicable to the VNF.
        required: false
      flavour_id:
        type: string
        description: Identifier of the Deployment Flavour within the VNFD
        required: true
      flavour_description:
        type: string
        description: Human readable description of the DF
        required: true
      vnf_profile:
        type: tosca.datatypes.nfv.VnfProfile
        description: Describes a profile for instantiating VNFs of a particular NS DF according to a specific VNFD and VNF DF
        required: false
    requirements:
      - virtual_link:
          capability: tosca.capabilities.nfv.VirtualLinkable
          relationship: tosca.relationships.nfv.VirtualLinksTo
          occurrences: [ 0, 1 ]
  # Additional requirements shall be defined in the VNF specific node type (deriving from tosca.nodes.nfv.VNF) corresponding to NS virtual links that need to connect to VnfExtCps
    interfaces:
      Vnflcm:
        type: tosca.interfaces.nfv.Vnflcm

  tosca.nodes.nfv.VnfExtCp:
    derived_from: tosca.nodes.nfv.Cp
    description: Describes a logical external connection point, exposed by the VNF enabling connection with an external Virtual Link
    properties:
      virtual_network_interface_requirements:
        type: list
        description: The actual virtual NIC requirements that is been assigned when instantiating the connection point
        required: false
        entry_schema:
          type: tosca.datatypes.nfv.VirtualNetworkInterfaceRequirements
    requirements:
      - external_virtual_link:
          capability: tosca.capabilities.nfv.VirtualLinkable
          relationship: tosca.relationships.nfv.VirtualLinksTo
      - internal_virtual_link: #name in ETSI NFV IFA011 v0.7.3: intVirtualLinkDesc
          capability: tosca.capabilities.nfv.VirtualLinkable
          relationship: tosca.relationships.nfv.VirtualLinksTo

  tosca.nodes.nfv.Vdu.Compute:
    derived_from: tosca.nodes.Root
    description:  Describes the virtual compute part of a VDU which is a construct   supporting the description of the deployment and operational behavior of a VNFC
    properties:
      name:
        type: string
        description: Human readable name of the VDU
        required: true
      description:
        type: string
        description: Human readable description of the VDU
        required: true
      boot_order:
        type: list # explicit index (boot index) not necessary, contrary to IFA011
        description: References a node template name from which a valid boot device is created
        required: false
        entry_schema:
          type: string
      nfvi_constraints:
        type: list
        description: Describes constraints on the NFVI for the VNFC instance(s) created from this VDU
        required: false
        entry_schema:
          type: string
      monitoring_parameters:
        type: list
        description: Describes monitoring parameters applicable to a VNFC instantiated from this VDU
        required: false
        entry_schema:
          type: tosca.datatypes.nfv.VnfcMonitoringParameter
      #configurable_properties:
         #type: tosca.datatypes.nfv.VnfcConfigurableProperties
         #required: false
         # derived types are expected to introduce
         # configurable_properties with its type derived from
         # tosca.datatypes.nfv.VnfcConfigurableProperties
      vdu_profile:
        type: tosca.datatypes.nfv.VduProfile
        description: Defines additional instantiation data for the VDU.Compute node
        required: true
      sw_image_data:
        type: tosca.datatypes.nfv.SwImageData
        description: Defines information related to a SwImage artifact used by this Vdu.Compute node
        required: false # property is required when the node template has an associated artifact of type tosca.artifacts.nfv.SwImage and not required otherwise
      boot_data:
        type: string
        description: Contains a string or a URL to a file contained in the VNF package used to customize a virtualised compute resource at boot time. The bootData may contain variable parts that are replaced by deployment specific values before being sent to the VIM.
        required: false
    capabilities:
      virtual_compute:
        type: tosca.capabilities.nfv.VirtualCompute
        occurrences: [ 1, 1 ]
      virtual_binding:
        type: tosca.capabilities.nfv.VirtualBindable
        occurrences: [ 1, UNBOUNDED ]
    requirements:
      - virtual_storage:
          capability: tosca.capabilities.nfv.VirtualStorage
          relationship: tosca.relationships.nfv.AttachesTo
          occurrences: [ 0, UNBOUNDED ]

  tosca.nodes.nfv.Vdu.VirtualBlockStorage:
    derived_from: tosca.nodes.Root
    description: This node type describes the specifications of requirements related to virtual block storage resources
    properties:
      virtual_block_storage_data:
        type: tosca.datatypes.nfv.VirtualBlockStorageData
        description: Describes the block storage characteristics.
        required: true
      sw_image_data:
        type: tosca.datatypes.nfv.SwImageData
        description: Defines information related to a SwImage artifact used by this Vdu.Compute node.
        required: false # property is required when the node template has an associated artifact of type tosca.artifacts.nfv.SwImage and not required otherwise
    capabilities:
      virtual_storage:
        type: tosca.capabilities.nfv.VirtualStorage
        description: Defines the capabilities of virtual_storage.

  tosca.nodes.nfv.Vdu.VirtualObjectStorage:
    derived_from: tosca.nodes.Root
    description: This node type describes the specifications of requirements related to virtual object storage resources
    properties:
      virtual_object_storage_data:
        type: tosca.datatypes.nfv.VirtualObjectStorageData
        description: Describes the object  storage characteristics.
        required: true
    capabilities:
      virtual_storage:
        type: tosca.capabilities.nfv.VirtualStorage
        description: Defines the capabilities of virtual_storage.

  tosca.nodes.nfv.Vdu.VirtualFileStorage:
    derived_from: tosca.nodes.Root
    description: This node type describes the specifications of requirements related to virtual file storage resources
    properties:
      virtual_file_storage_data:
        type: tosca.datatypes.nfv.VirtualFileStorageData
        description: Describes the file  storage characteristics.
        required: true
    capabilities:
      virtual_storage:
        type: tosca.capabilities.nfv.VirtualStorage
        description: Defines the capabilities of virtual_storage.
    requirements:
     - virtual_link:
         capability: tosca.capabilities.nfv.VirtualLinkable
         relationship: tosca.relationships.nfv.VirtualLinksTo
         #description: Describes the requirements for linking to virtual link

  tosca.nodes.nfv.VduCp:
    derived_from: tosca.nodes.nfv.Cp
    description: describes network connectivity between a VNFC instance based on this VDU and an internal VL
    properties:
      bitrate_requirement:
        type: integer   # in bits per second
        description: Bitrate requirement in bit per second on this connection point
        required: false
        constraints:
          - greater_or_equal: 0
      virtual_network_interface_requirements:
        type: list
        description: Specifies requirements on a virtual network interface realising the CPs instantiated from this CPD
        required: false
        entry_schema:
          type: tosca.datatypes.nfv.VirtualNetworkInterfaceRequirements
      order:
        type: integer
        description: The order of the NIC on the compute instance (e.g.eth2)
        required: false
        constraints:
          - greater_or_equal: 0
      vnic_type:
        type: string
        description: Describes the type of the virtual network interface realizing the CPs instantiated from this CPD
        required: false
        constraints:
        - valid_values: [ normal, virtio, direct-physical ]
    requirements:
      - virtual_link:
          capability: tosca.capabilities.nfv.VirtualLinkable
          relationship: tosca.relationships.nfv.VirtualLinksTo
      - virtual_binding:
          capability: tosca.capabilities.nfv.VirtualBindable
          relationship: tosca.relationships.nfv.VirtualBindsTo
          node: tosca.nodes.nfv.Vdu.Compute

  tosca.nodes.nfv.VnfVirtualLink:
    derived_from: tosca.nodes.Root
    description: Describes the information about an internal VNF VL
    properties:
      connectivity_type:
        type: tosca.datatypes.nfv.ConnectivityType
        description: Specifies the protocol exposed by the VL and the flow pattern supported by the VL
        required: true
      description:
        type: string
        description: Provides human-readable information on the purpose of the VL
        required: false
      test_access:
        type: list
        description: Test access facilities available on the VL
        required: false
        entry_schema:
          type: string
          constraints:
            - valid_values: [ passive_monitoring, active_loopback ]
      vl_profile:
        type: tosca.datatypes.nfv.VlProfile
        description: Defines additional data for the VL
        required: true
      monitoring_parameters:
        type: list
        description: Describes monitoring parameters applicable to the VL
        required: false
        entry_schema:
          type: tosca.datatypes.nfv.VirtualLinkMonitoringParameter
    capabilities:
      virtual_linkable:
        type: tosca.capabilities.nfv.VirtualLinkable

group_types:
  tosca.groups.nfv.PlacementGroup:
    derived_from: tosca.groups.Root
    description: PlacementGroup is used for describing the affinity or anti-affinity relationship applicable between the virtualization containers to be created based on different VDUs, or between internal VLs to be created based on different VnfVirtualLinkDesc(s)
    properties:
      description:
        type: string
        description: Human readable description of the group
        required: true
    members: [ tosca.nodes.nfv.Vdu.Compute, tosca.nodes.nfv.VnfVirtualLink ]

policy_types:
  tosca.policies.nfv.InstantiationLevels:
    derived_from: tosca.policies.Root
    description: The InstantiationLevels type is a policy type representing all the instantiation levels of resources to be instantiated within a deployment flavour and including default instantiation level in term of the number of VNFC instances to be created as defined in ETSI GS NFV-IFA 011 [1].
    properties:
      levels:
        type: map # key: levelId
        description: Describes the various levels of resources that can be used to instantiate the VNF using this flavour.
        required: true
        entry_schema:
          type: tosca.datatypes.nfv.InstantiationLevel
        constraints:
          - min_length: 1
      default_level:
        type: string # levelId
        description: The default instantiation level for this flavour.
        required: false # required if multiple entries in levels

  tosca.policies.nfv.VduInstantiationLevels:
    derived_from: tosca.policies.Root
    description:  The VduInstantiationLevels type is a policy type representing all the instantiation levels of resources to be instantiated within a deployment flavour in term of the number of VNFC instances to be created from each vdu.Compute. as defined in ETSI GS NFV-IFA 011 [1]
    properties:
      levels:
        type: map # key: levelId
        description: Describes the Vdu.Compute levels of resources that can be used to instantiate the VNF using this flavour
        required: true
        entry_schema:
          type: tosca.datatypes.nfv.VduLevel
        constraints:
          - min_length: 1
    targets: [ tosca.nodes.nfv.Vdu.Compute ]

  tosca.policies.nfv.VirtualLinkInstantiationLevels:
    derived_from: tosca.policies.Root
    description:  The VirtualLinkInstantiationLevels type is a policy type representing all the instantiation levels of virtual link resources to be instantiated within a deployment flavour as defined in ETSI GS NFV-IFA 011 [1].
    properties:
      levels:
        type: map # key: levelId
        description: Describes the virtual link levels of resources that can be used to instantiate the VNF using this flavour.
        required: true
        entry_schema:
          type: tosca.datatypes.nfv.VirtualLinkBitrateLevel
        constraints:
          - min_length: 1
    targets: [ tosca.nodes.nfv.VnfVirtualLink ]

  tosca.policies.nfv.ScalingAspects:
    derived_from: tosca.policies.Root
    description: The ScalingAspects type is a policy type representing the scaling aspects used for horizontal scaling as defined in ETSI GS NFV-IFA 011 [1].
    properties:
      aspects:
        type: map # key: aspectId
        description: Describe maximum scale level for total number of scaling steps that can be applied to a particular aspect
        required: true
        entry_schema:
          type: tosca.datatypes.nfv.ScalingAspect
        constraints:
          - min_length: 1

  tosca.policies.nfv.VduScalingAspectDeltas:
    derived_from: tosca.policies.Root
    description: The VduScalingAspectDeltas type is a policy type representing the Vdu.Compute detail of an aspect deltas used for horizontal scaling, as defined in ETSI GS NFV-IFA 011 [1].
    properties:
      aspect:
        type: string
        description: Represents the scaling aspect to which this policy applies
        required: true
      deltas:
        type: map # key: scalingDeltaId
        description: Describes the Vdu.Compute scaling deltas to be applied for every scaling steps of a particular aspect.
        required: true
        entry_schema:
          type: tosca.datatypes.nfv.VduLevel
        constraints:
          - min_length: 1
    targets: [ tosca.nodes.nfv.Vdu.Compute ]

  tosca.policies.nfv.VirtualLinkBitrateScalingAspectDeltas:
    derived_from: tosca.policies.Root
    description: The VirtualLinkBitrateScalingAspectDeltas type is a policy type representing the VnfVirtualLink detail of an aspect deltas used for horizontal scaling, as defined in ETSI GS NFV-IFA 011 [1].
    properties:
      aspect:
        type: string
        description: Represents the scaling aspect to which this policy applies.
        required: true
      deltas:
        type: map # key: scalingDeltaId
        description: Describes the VnfVirtualLink scaling deltas to be applied for every scaling steps of a particular aspect.
        required: true
        entry_schema:
          type: tosca.datatypes.nfv.VirtualLinkBitrateLevel
        constraints:
          - min_length: 1
    targets: [ tosca.nodes.nfv.VnfVirtualLink ]

  tosca.policies.nfv.VduInitialDelta:
    derived_from: tosca.policies.Root
    description: The VduInitialDelta type is a policy type representing the Vdu.Compute detail of an initial delta used for horizontal scaling, as defined in ETSI GS NFV-IFA 011 [1].
    properties:
      initial_delta:
        type: tosca.datatypes.nfv.VduLevel
        description: Represents the initial minimum size of the VNF.
        required: true
    targets: [ tosca.nodes.nfv.Vdu.Compute ]

  tosca.policies.nfv.VirtualLinkBitrateInitialDelta:
    derived_from: tosca.policies.Root
    description: The VirtualLinkBitrateInitialDelta type is a policy type representing the VnfVirtualLink detail of an initial deltas used for horizontal scaling, as defined in ETSI GS NFV-IFA 011 [1].
    properties:
      initial_delta:
        type: tosca.datatypes.nfv.VirtualLinkBitrateLevel
        description: Represents the initial minimum size of the VNF.
        required: true
    targets: [ tosca.nodes.nfv.VnfVirtualLink ]

  tosca.policies.nfv.AffinityRule:
    derived_from: tosca.policies.Placement
    description: The AffinityRule describes the affinity rules applicable for the defined targets
    properties:
      scope:
        type: string
        description: scope of the rule is an NFVI_node, an NFVI_PoP, etc.
        required: true
        constraints:
          - valid_values: [ nfvi_node, zone, zone_group, nfvi_pop ]
    targets: [ tosca.nodes.nfv.Vdu.Compute, tosca.nodes.nfv.VnfVirtualLink, tosca.groups.nfv.PlacementGroup ]

  tosca.policies.nfv.AntiAffinityRule:
    derived_from: tosca.policies.Placement
    description: The AntiAffinityRule describes the anti-affinity rules applicable for the defined targets
    properties:
      scope:
        type: string
        description: scope of the rule is an NFVI_node, an NFVI_PoP, etc.
        required: true
        constraints:
          - valid_values: [ nfvi_node, zone, zone_group, nfvi_pop ]
    targets: [ tosca.nodes.nfv.Vdu.Compute, tosca.nodes.nfv.VnfVirtualLink, tosca.groups.nfv.PlacementGroup ]

  tosca.policies.nfv.SecurityGroupRule:
    derived_from: tosca.policies.Root
    description: The SecurityGroupRule type is a policy type specified the matching criteria for the ingress and/or egress traffic to/from visited connection points as defined in ETSI GS NFV-IFA 011 [1].
    properties:
      description:
        type: string
        description: Human readable description of the security group rule.
        required: false
      direction:
        type: string
        description: The direction in which the security group rule is applied. The direction of 'ingress' or 'egress' is specified against the associated CP. I.e., 'ingress' means the packets entering a CP, while 'egress' means the packets sent out of a CP.
        required: false
        constraints:
          - valid_values: [ ingress, egress ]
        default: ingress
      ether_type:
        type: string
        description: Indicates the protocol carried over the Ethernet layer.
        required: false
        constraints:
          - valid_values: [ ipv4, ipv6 ]
        default: ipv4
      protocol:
        type: string
        description: Indicates the protocol carried over the IP layer. Permitted values include any protocol defined in the IANA protocol registry, e.g. TCP, UDP, ICMP, etc.
        required: false
        constraints:
          - valid_values: [ hopopt, icmp, igmp, ggp, ipv4, st, tcp, cbt, egp, igp, bbn_rcc_mon, nvp_ii, pup, argus, emcon, xnet, chaos, udp, mux, dcn_meas, hmp, prm, xns_idp, trunk_1, trunk_2, leaf_1, leaf_2, rdp, irtp, iso_tp4, netblt, mfe_nsp, merit_inp, dccp, 3pc, idpr, xtp, ddp, idpr_cmtp, tp++, il, ipv6, sdrp, ipv6_route, ipv6_frag, idrp, rsvp, gre, dsr, bna, esp, ah, i_nlsp, swipe, narp, mobile, tlsp, skip, ipv6_icmp, ipv6_no_nxt, ipv6_opts, cftp, sat_expak, kryptolan, rvd, ippc, sat_mon, visa, ipcv, cpnx, cphb, wsn, pvp, br_sat_mon, sun_nd, wb_mon, wb_expak, iso_ip, vmtp, secure_vmtp, vines, ttp, iptm, nsfnet_igp, dgp, tcf, eigrp, ospfigp, sprite_rpc, larp, mtp, ax.25, ipip, micp, scc_sp, etherip, encap, gmtp, ifmp, pnni, pim, aris, scps, qnx, a/n, ip_comp, snp, compaq_peer, ipx_in_ip, vrrp, pgm, l2tp, ddx, iatp, stp, srp, uti, smp, sm, ptp, isis, fire, crtp, crudp, sscopmce, iplt, sps, pipe, sctp, fc, rsvp_e2e_ignore, mobility, udp_lite, mpls_in_ip, manet, hip, shim6, wesp, rohc ]
        default: tcp
      port_range_min:
        type: integer
        description: Indicates minimum port number in the range that is matched by the security group rule. If a value is provided at design-time, this value may be overridden at run-time based on other deployment requirements or constraints.
        required: false
        constraints:
          - greater_or_equal: 0
          - less_or_equal: 65535
        default: 0
      port_range_max:
        type: integer
        description: Indicates maximum port number in the range that is matched by the security group rule. If a value is provided at design-time, this value may be overridden at run-time based on other deployment requirements or constraints.
        required: false
        constraints:
          - greater_or_equal: 0
          - less_or_equal: 65535
        default: 65535
    targets: [ tosca.nodes.nfv.VduCp, tosca.nodes.nfv.VnfExtCp ]

  tosca.policies.nfv.SupportedVnfInterface:
    derived_from: tosca.policies.Root
    description:  this policy type represents interfaces produced by a VNF, the details to access them and the applicable connection points to use to access these interfaces
    properties:
      interface_name:
        type: string
        description: Identifies an interface produced by the VNF.
        required: true
        constraints:
          - valid_values: [ vnf_indicator, vnf_configuration ]
      details:
        type: tosca.datatypes.nfv.InterfaceDetails
        description: Provide additional data to access the interface endpoint
        required: false
    targets: [ tosca.nodes.nfv.VnfExtCp, tosca.nodes.nfv.VduCp ]
"""

#create file in http server directory.
if not os.path.exists(os.path.dirname(filename)):
    try:
        os.makedirs(os.path.dirname(filename))
    except OSError as exc: # Guard against race condition
        if exc.errno != errno.EEXIST:
            raise
    with open(filename, "w") as file:
        file.write(etsi_nfv_sol001_vnfd_types)
        file.close()


MSA_API.task_success('VNFD TOSCA Sol001 schema was created successfully.', context, True)