<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args() {
}

if (isset($context['servers_scaled'])) {
	foreach ($context['servers_scaled'] as &$server) {
		if (isset($server['device_id']) && $server['server_id'] == $context['server_id']) {
			$device_id = substr($server['device_id'], 3);
			$response = _device_delete($device_id);
			$response = json_decode($response, true);
			if ($response['wo_status'] !== ENDED) {
				$response = json_encode($response);
				echo $response;
				exit;
			}
		}
		unset($server);
	}
}

$response = prepare_json_response(ENDED, "MSA Device deleted successfully.", $context, true);
echo $response;

?>

