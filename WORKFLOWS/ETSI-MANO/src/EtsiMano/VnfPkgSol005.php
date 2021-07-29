<?php
namespace Ubiqube\EtsiMano;

class VnfPkgSol005 extends BaseApi
{

	const BASE_URL = 'sol005/vnfpkgm/v1/vnf_packages';

	/**
	 *
	 * @param string $body
	 * @return unknown
	 */
	public function vnfPackagesPost($_body)
	{
		$url_frag = self::BASE_URL;
		return json_decode($this->doPost($url_frag, $_body), 1);
	}

	/**
	 *
	 * @param string $_vnfPkgId
	 * @param string $_body
	 * @return void
	 */
	public function vnfPackagesVnfPkgIdPatch($_vnfPkgId, $_body)
	{
		$url_frag = self::BASE_URL . '/' . urlencode($_vnfPkgId);
		return json_decode($this->doPatch($url_frag, $_body), 1);
	}

	public function vnfPackagesVnfPkgIdDelete($_vnfPkgId)
	{
		$url_frag = self::BASE_URL . '/' . $_vnfPkgId;
		return $this->doDelete($url_frag);
	}

	public function vnfPackagesVnfPkgIdPackageContentPut($_vnfPkgId, $_content)
	{
		$url_frag = self::BASE_URL . '/' . $_vnfPkgId . '/package_content';
		return $this->doPutMp($url_frag, $_content);
	}

	public function vnfPackagesVnfPkgIdPackageFilePut($_vnfPkgId, $_filename)
        {
		$base = "/opt/fmc_repository/Process/ETSI-MANO/src/EtsiMano";
                return shell_exec("$base/onboard_vnf.sh $_vnfPkgId $_filename 2>&1");;
        }

	public function setOperationalState($_vnfPkgId, $_state)
	{
		$content = '{ "operationalState": "DISABLED" }';
		if ($_state == 'true') {
			$content = '{ "operationalState": "ENABLED" }';
		}
		$this->vnfPackagesVnfPkgIdPatch($_vnfPkgId, $content);
	}

	public function exposeDocument($_documentType, $_id)
	{
		$url_frag = 'expose/' . urlencode($_documentType) . '/' . urlencode($_id);
		return json_decode($this->doPost($url_frag, '{}'), 1);
	}
}
